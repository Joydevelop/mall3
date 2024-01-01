package com.joy.coupon.service.impl;

import com.joy.common.to.MemberPrice;
import com.joy.common.to.SkuReductionTo;
import com.joy.coupon.entity.MemberPriceEntity;
import com.joy.coupon.entity.SkuLadderEntity;
import com.joy.coupon.service.MemberPriceService;
import com.joy.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joy.common.utils.PageUtils;
import com.joy.common.utils.Query;

import com.joy.coupon.dao.SkuFullReductionDao;
import com.joy.coupon.entity.SkuFullReductionEntity;
import com.joy.coupon.service.SkuFullReductionService;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {
    @Autowired
    private SkuLadderService skuLadderService;
    @Autowired
    private MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkuReductionTo skuReductionTo) {
        // 1. 保存满减打折
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(skuReductionTo.getSkuId());
        skuLadderEntity.setFullCount(skuReductionTo.getFullCount());
        skuLadderEntity.setDiscount(skuReductionTo.getDiscount());
        skuLadderEntity.setAddOther(skuReductionTo.getCountStatus());
        if (skuReductionTo.getFullCount() > 0) {
            skuLadderService.save(skuLadderEntity);
        }
        // 2. 保存满减信息
        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuReductionTo, skuFullReductionEntity);
        if (skuFullReductionEntity.getFullPrice().compareTo(BigDecimal.ZERO) > 0) {
            this.save(skuFullReductionEntity);
        }
        // 3.会员价格
        List<MemberPrice> memberPrice = skuReductionTo.getMemberPrice();
        List<MemberPriceEntity> memberPriceEntities = memberPrice.stream().map(item -> {
                                                                     MemberPriceEntity priceEntity = new MemberPriceEntity();
                                                                     priceEntity.setSkuId(skuReductionTo.getSkuId());
                                                                     priceEntity.setMemberLevelId(item.getId());
                                                                     priceEntity.setMemberLevelName(item.getName());
                                                                     priceEntity.setMemberPrice(item.getPrice());
                                                                     priceEntity.setAddOther(1);
                                                                     return priceEntity;
                                                                 }).filter(item -> item.getMemberPrice().compareTo(BigDecimal.ZERO) > 0)

                                                                 .toList();
        memberPriceService.saveBatch(memberPriceEntities);
    }

}