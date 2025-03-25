package com.elon.hypesphere.coupon.service;

import com.elon.hypesphere.common.to.SkuReductionTo;
import com.elon.hypesphere.coupon.entity.SkuFullReduction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品满减信息 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface ISkuFullReductionService extends IService<SkuFullReduction> {

    // 保存sku优惠信息
    void saveSkuReduction(SkuReductionTo skuReductionTo);
}
