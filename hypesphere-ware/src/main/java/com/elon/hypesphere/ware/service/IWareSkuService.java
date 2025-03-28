package com.elon.hypesphere.ware.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.ware.entity.WareSku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 商品库存 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IWareSkuService extends IService<WareSku> {

    // 分页查询
    PageUtils queryPage(Map<String, Object> params);

    // 保存库存
    void addStock(Long skuId, Long wareId, Integer skuNum);
}
