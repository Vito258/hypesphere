package com.elon.hypesphere.ware.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.ware.entity.Purchase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elon.hypesphere.ware.vo.MergeVo;
import com.elon.hypesphere.ware.vo.PurchaseDoneVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 采购单 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IPurchaseService extends IService<Purchase> {

    // 分页查询
    PageUtils queryPage(Map<String, Object> params);

    // 分页查询未领取的采购单
    PageUtils queryPageUnreceive(Map<String, Object> params);

    // 合并采购需求
    void mergePurchase(MergeVo mergeVo);

    // 领取采购单
    void received(List<Long> ids);

    // 完成采购单
    void done(PurchaseDoneVo doneVo);
}
