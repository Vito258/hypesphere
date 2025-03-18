package com.elon.hypesphere.product.service;

import com.elon.hypesphere.product.entity.CategoryBrandRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌分类关联 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface ICategoryBrandRelationService extends IService<CategoryBrandRelation> {

    // 保存关联关系
    void saveDetail(CategoryBrandRelation categoryBrandRelation);
}
