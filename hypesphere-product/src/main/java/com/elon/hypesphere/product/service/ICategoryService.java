package com.elon.hypesphere.product.service;

import com.elon.hypesphere.product.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
