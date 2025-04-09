package com.elon.hypesphere.product.service;

import com.elon.hypesphere.product.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elon.hypesphere.product.vo.Catalog2Vo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface ICategoryService extends IService<Category> {

    List<Category> listWithTree();

    void removeMenuByIds(List<Long> asList);

    // 查询三级分类
    Long[] findCatelogPath(Long catelogId);

    // 更新三级分类
    void updateDetail(Category category);

    // 查询一级分类
    List<Category> getLevel1Categories();

    // 查询分类数据
    Map<String, List<Catalog2Vo>> getCatalogJson();
}
