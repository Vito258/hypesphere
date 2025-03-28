package com.elon.hypesphere.ware.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.ware.entity.PurchaseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 采购需求 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IPurchaseDetailService extends IService<PurchaseDetail> {

    PageUtils queryPage(Map<String, Object> params);
}
