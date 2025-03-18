package com.elon.hypesphere.product.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.product.entity.Attr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IAttrService extends IService<Attr> {

    // 分页查询
    PageUtils queryPage(Map<String, Object> params);
}
