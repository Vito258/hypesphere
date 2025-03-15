package com.elon.hypesphere.product.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.product.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 品牌 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IBrandService extends IService<Brand> {

    // 分页查询
    PageUtils queryPage(Map<String, Object> params);

    // 更新品牌详情
    void updateDetail(Brand brand);
}
