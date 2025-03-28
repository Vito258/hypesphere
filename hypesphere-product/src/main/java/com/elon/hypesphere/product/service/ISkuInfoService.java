package com.elon.hypesphere.product.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.product.entity.SkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface ISkuInfoService extends IService<SkuInfo> {

    // 分页查询
    PageUtils queryPageByCondition(Map<String, Object> params);
}
