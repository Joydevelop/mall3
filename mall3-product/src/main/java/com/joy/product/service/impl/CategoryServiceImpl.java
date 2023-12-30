package com.joy.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joy.common.utils.PageUtils;
import com.joy.common.utils.Query;

import com.joy.product.dao.CategoryDao;
import com.joy.product.entity.CategoryEntity;
import com.joy.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
        //所有一级分类
        return entities
                .stream()
                .filter(item -> item.getParentCid() == 0)
                .peek(item -> item.setChildren(getChildren(item, entities)))
                .sorted(Comparator.comparingInt(m -> (m.getSort() == null ? 0 : m.getSort())))
                .toList();
    }

    /**
     * 递归
     * @param root
     * @param all
     * @return
     */
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream()
                  .filter(item -> item.getParentCid().equals(root.getCatId()))
                  .peek(item -> item.setChildren(getChildren(item, all)))
                  .sorted(Comparator.comparingInt(m -> (m.getSort() == null ? 0 : m.getSort())))
                  .toList();
    }
}