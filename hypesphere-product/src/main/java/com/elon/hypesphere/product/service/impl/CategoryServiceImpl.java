package com.elon.hypesphere.product.service.impl;

import com.elon.hypesphere.product.entity.Category;
import com.elon.hypesphere.product.mapper.CategoryMapper;
import com.elon.hypesphere.product.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
