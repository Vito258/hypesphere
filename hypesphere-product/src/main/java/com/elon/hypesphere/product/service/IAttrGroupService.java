package com.elon.hypesphere.product.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.product.entity.AttrGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IAttrGroupService extends IService<AttrGroup> {

    // 分页查询所有属性分组
    PageUtils queryPage(Map<String, Object> params);

    // 根据分类id查询属性分组
    PageUtils queryPage(Map<String, Object> params, Long catelogId);
}
