package com.elon.hypesphere.product.service.impl;

import com.elon.hypesphere.product.entity.CategoryBrandRelation;
import com.elon.hypesphere.product.mapper.BrandMapper;
import com.elon.hypesphere.product.mapper.CategoryBrandRelationMapper;
import com.elon.hypesphere.product.mapper.CategoryMapper;
import com.elon.hypesphere.product.service.IBrandService;
import com.elon.hypesphere.product.service.ICategoryBrandRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elon.hypesphere.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌分类关联 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation> implements ICategoryBrandRelationService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void saveDetail(CategoryBrandRelation categoryBrandRelation) {
        // 获取品牌名、分类名
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();

        // 设置品牌名、分类名
        categoryBrandRelation.setBrandName(brandMapper.selectById(brandId).getName());
        categoryBrandRelation.setCatelogName(categoryMapper.selectById(catelogId).getName());

        // 执行保存方法
        save(categoryBrandRelation);
    }
}
