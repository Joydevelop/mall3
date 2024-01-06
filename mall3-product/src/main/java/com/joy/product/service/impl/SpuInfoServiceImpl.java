package com.joy.product.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.common.utils.MapUtil;
import com.alibaba.nacos.shaded.io.grpc.internal.JsonUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.joy.common.to.SkuReductionTo;
import com.joy.common.to.SpuBoundTo;
import com.joy.common.to.es.SkuEsModel;
import com.joy.common.utils.R;
import com.joy.product.entity.*;
import com.joy.product.feign.CouponFeignService;
import com.joy.product.feign.SearchFeignService;
import com.joy.product.feign.WareFeignService;
import com.joy.product.service.*;
import com.joy.product.vo.*;
import jakarta.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joy.common.utils.PageUtils;
import com.joy.common.utils.Query;

import com.joy.product.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {
    @Autowired
    private SpuInfoDescService spuInfoDescService;
    @Autowired
    private SpuImagesService spuImagesService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuImagesService imagesService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private CouponFeignService couponFeignService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Resource
    private WareFeignService wareFeignService;
    @Resource
    private SearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveSpuInfo(SpuSaveVo vo) {
        // 1.保存spu基本信息 pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(vo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(spuInfoEntity);

        // 2.保存spu的描述图片 pms_spu_info_desc
        List<String> decript = vo.getDecript();
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
        spuInfoDescEntity.setDecript(String.join(",", decript));
        spuInfoDescService.saveSpuInfoDesc(spuInfoDescEntity);

        // 3.保存spu的图片集 pms_spu_images
        List<String> images = vo.getImages();
        spuImagesService.saveImages(spuInfoEntity.getId(), images);

        // 4.保存spu的规格参数 pms_product_attr_value
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValueEntity> list = baseAttrs.stream().map(item -> {
            ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
            valueEntity.setAttrId(item.getAttrId());
            AttrEntity attr = attrService.getById(item.getAttrId());
            valueEntity.setAttrName(attr.getAttrName());
            valueEntity.setAttrValue(item.getAttrValues());
            valueEntity.setQuickShow(item.getShowDesc());
            valueEntity.setSpuId(spuInfoEntity.getId());
            return valueEntity;
        }).toList();
        productAttrValueService.saveProductAttr(list);

        // 保存spu的积分信息 sms_spu_bounds

        // 5.保存spu对应的sku信息
        List<Skus> skus = vo.getSkus();
        skus.forEach(item -> {
            String defaultImg = "";
            for (Images image : item.getImages()) {
                if (image.getDefaultImg() == 1) {
                    defaultImg = image.getImgUrl();
                }
            }
            // 5.1 sku的基本信息 pms_sku_info
            SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
            BeanUtils.copyProperties(item, skuInfoEntity);
            skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
            skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
            skuInfoEntity.setSaleCount(0L);
            skuInfoEntity.setSpuId(spuInfoEntity.getId());
            skuInfoEntity.setSkuDefaultImg(defaultImg);
            skuInfoService.saveSkuInfo(skuInfoEntity);
            Long skuId = skuInfoEntity.getSkuId();

            // 5.2 sku的图片信息 pms_sku_images
            List<SkuImagesEntity> skuImagesEntities = item.getImages()
                                                          .stream()
                                                          .map(img -> {
                                                              SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                                                              skuImagesEntity.setSkuId(skuId);
                                                              skuImagesEntity.setImgUrl(img.getImgUrl());
                                                              skuImagesEntity.setDefaultImg(img.getDefaultImg());
                                                              return skuImagesEntity;
                                                          }).filter(img -> StringUtils.isNotBlank(img.getImgUrl()))
                                                          .toList();
            imagesService.saveBatch(skuImagesEntities);

            // 5.3 sku的销售属性信息 pms_sku_sale_attr_value
            List<Attr> attr = item.getAttr();
            List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                BeanUtils.copyProperties(a, skuSaleAttrValueEntity);
                skuSaleAttrValueEntity.setSkuId(skuId);
                return skuSaleAttrValueEntity;
            }).toList();
            skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

            // 5.4 sku的优惠、满减等信息 gms_sku_ladder、gms_sku_full_reduction、gms_member_price
            SpuBoundTo spuBoundTo = new SpuBoundTo();
            Bounds bounds = vo.getBounds();
            BeanUtils.copyProperties(bounds, spuBoundTo);
            couponFeignService.saveSpuBounds(spuBoundTo);

            SkuReductionTo skuReductionTo = new SkuReductionTo();
            BeanUtils.copyProperties(item, skuReductionTo);
            skuReductionTo.setSkuId(skuId);
            if (skuReductionTo.getFullCount() > 0 || skuReductionTo.getFullPrice().compareTo(BigDecimal.ZERO) > 0) {
                couponFeignService.saveSkuReduction(skuReductionTo);
            }
        });
    }

    @Override
    public void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity) {
        this.baseMapper.insert(spuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<SpuInfoEntity>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper.and((w) -> {
                w.eq("id", key).or().like("spu_name", key);
            });
        }
        String status = (String) params.get("status");
        if (StringUtils.isNotBlank(status)) {
            wrapper.eq("publish_status", status);
        }
        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotBlank(brandId) && !"0".equals(brandId)) {
            wrapper.eq("brand_id", brandId);
        }
        String catelogId = (String) params.get("catelogId");
        if (StringUtils.isNotBlank(catelogId) && !"0".equals(catelogId)) {
            wrapper.eq("catalog_id", catelogId);
        }
        IPage<SpuInfoEntity> page = this.page(new Query<SpuInfoEntity>().getPage(params), wrapper);
        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {
        // 1.查出当前spuId对应的所有sku信息，品牌的名字
        List<SkuInfoEntity> skus = skuInfoService.getSkusBySpuId(spuId);

        List<Long> skuIds = skus.stream().map(SkuInfoEntity::getSkuId).toList();

        List<ProductAttrValueEntity> baseAttrs = productAttrValueService.baseAttrListForSpu(spuId);
        List<Long> attrIds = baseAttrs.stream().map(ProductAttrValueEntity::getAttrId).toList();
        List<Long> searchAttrIds = attrService.selectSearchAttrs(attrIds);
        Set<Long> idSet = new HashSet<>(searchAttrIds);
        List<SkuEsModel.Attr> attrList = baseAttrs.stream().filter(item -> idSet.contains(item.getAttrId()))
                                                  .map(item -> {
                                                      SkuEsModel.Attr attr = new SkuEsModel.Attr();
                                                      BeanUtils.copyProperties(item, attr);
                                                      return attr;
                                                  })
                                                  .toList();
        //设置库存信息
        R<List<SkuHasStockVo>> skuHasStock = wareFeignService.getSkuHasStock(skuIds);
        List<SkuHasStockVo> skuHasStockVos = skuHasStock.getData();
        Map<Long, Boolean> stockMap;
        if (!CollectionUtils.isEmpty(skuHasStockVos)){
            stockMap = skuHasStockVos.stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::getHasStock));
        } else {
            stockMap = null;
        }


        List<SkuEsModel> list = skus.stream().map(sku -> {
            SkuEsModel skuEsModel = new SkuEsModel();
            BeanUtils.copyProperties(sku, skuEsModel);
            skuEsModel.setSkuPrice(sku.getPrice());
            skuEsModel.setSkuImg(sku.getSkuDefaultImg());
            //热度评分
            skuEsModel.setHotScore(0L);
            skuEsModel.setHasStock(!MapUtil.isEmpty(stockMap) && stockMap.get(sku.getSkuId()));
            //查询品牌和分类的名字
            BrandEntity brandEntity = brandService.getById(sku.getBrandId());
            skuEsModel.setBrandName(brandEntity.getName());
            skuEsModel.setBrandImg(brandEntity.getLogo());
            CategoryEntity categoryEntity = categoryService.getById(sku.getCatalogId());
            skuEsModel.setCatalogName(categoryEntity.getName());
            skuEsModel.setAttrs(attrList);
            return skuEsModel;

        }).toList();
        searchFeignService.productStatusUp(list);
        //修改上架状态
        baseMapper.updateSpuStatus(spuId, 1);
    }

}