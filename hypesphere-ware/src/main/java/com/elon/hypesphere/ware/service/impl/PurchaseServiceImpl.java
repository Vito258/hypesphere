package com.elon.hypesphere.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elon.hypesphere.common.constant.WareConstant;
import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.Query;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.ware.entity.Purchase;
import com.elon.hypesphere.ware.entity.PurchaseDetail;
import com.elon.hypesphere.ware.mapper.PurchaseMapper;
import com.elon.hypesphere.ware.service.IPurchaseDetailService;
import com.elon.hypesphere.ware.service.IPurchaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elon.hypesphere.ware.service.IWareSkuService;
import com.elon.hypesphere.ware.vo.MergeVo;
import com.elon.hypesphere.ware.vo.PurchaseDoneVo;
import com.elon.hypesphere.ware.vo.PurchaseItemDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 采购单 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements IPurchaseService {

    @Autowired
    private IPurchaseDetailService purchaseDetailService;

    @Autowired
    private IWareSkuService wareSkuService;

    /**
     * 采购单分页查询
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Purchase> page = this.page(
                new Query<Purchase>().getPage(params),
                new QueryWrapper<Purchase>()
        );

        return new PageUtils(page);
    }

    /**
     * 采购单未领取分页查询
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPageUnreceive(Map<String, Object> params) {
        IPage<Purchase> page = this.page(
                new Query<Purchase>().getPage(params),
                new QueryWrapper<Purchase>().eq("status", 0).or().eq("status", 1)
        );

        return new PageUtils(page);
    }

    /**
     * 合并采购需求
     *
     * @param mergeVo
     */
    @Transactional
    @Override
    public void mergePurchase(MergeVo mergeVo) {
        // 1、判断是否需要新建采购单
        Long purchaseId = mergeVo.getPurchaseId();
        Purchase purchase = null;
        if (purchaseId == null) {
            // 没有采购单，需要新建
            purchase = new Purchase();
            purchase.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            this.save(purchase);
        } else {
            purchase = this.getById(purchaseId);
            // 确定已有的采购的状态是否能合并
            if (purchase.getStatus() != WareConstant.PurchaseStatusEnum.CREATED.getCode() && purchase.getStatus() != WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
                // 采购单不是新建或已分配，无法合并
                return;
            }
        }

        // 2、合并采购需求
        Long finalPurchaseId = purchase.getId();
        List<Long> items = mergeVo.getItems();
        List<PurchaseDetail> purchaseDetails = items.stream().map(i -> {
            // 批量修改采购需求
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setId(i);
            purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
            purchaseDetail.setPurchaseId(finalPurchaseId);
            return purchaseDetail;
        }).collect(Collectors.toList());

        purchaseDetailService.updateBatchById(purchaseDetails);
    }

    /**
     * 领取采购单
     * @param ids 采购订单的ID
     */
    @Transactional
    @Override
    public void received(List<Long> ids) {
        // 1、更新采购单的信息
        List<Purchase> purchases = ids.stream().map(this::getById).filter(i -> {
            return i.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() || i.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode();
        }).peek(i -> i.setStatus(WareConstant.PurchaseStatusEnum.RECEIVED.getCode())).collect(Collectors.toList());

        updateBatchById(purchases);

        // 2、更新采购项的信息
        purchases.forEach(purchase -> {
            List<PurchaseDetail> purchaseDetails = purchaseDetailService.list(new QueryWrapper<PurchaseDetail>().eq("purchase_id", purchase.getId()));
            purchaseDetails.forEach(purchaseDetail -> {
                if (purchaseDetail.getStatus() == WareConstant.PurchaseDetailStatusEnum.CREATED.getCode() || purchaseDetail.getStatus() == WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode()) {
                    purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
                }
            });
            purchaseDetailService.updateBatchById(purchaseDetails);
        });
    }

    /**
     * 完成采购
     * @param doneVo
     */
    @Transactional
    @Override
    public void done(PurchaseDoneVo doneVo) {
        List<PurchaseItemDoneVo> items = doneVo.getItems();

        // 1、改变采购项状态
        boolean flag = true;
        List<PurchaseDetail> purchaseDetails = new ArrayList<>();
        for (PurchaseItemDoneVo item : items) {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            if (item.getStatus() == WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode()) {
                // 采购失败的情况
                flag = false;
                purchaseDetail.setStatus(item.getStatus());
            }else{
                purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.FINISHED.getCode());

                // 1.1 保存采购项到数据库
                PurchaseDetail byId = purchaseDetailService.getById(item.getItemId());
                wareSkuService.addStock(byId.getSkuId(), byId.getWareId(), byId.getSkuNum());
            }

            purchaseDetail.setId(item.getItemId());
            purchaseDetails.add(purchaseDetail);
        }
        purchaseDetailService.updateBatchById(purchaseDetails);

        // 2、改变采购单状态
        // 当采购单中的所有项都是完成状态，才改变采购单状态
        Purchase purchase = new Purchase();
        purchase.setId(doneVo.getId());
        purchase.setStatus(flag ? WareConstant.PurchaseStatusEnum.FINISHED.getCode() : WareConstant.PurchaseStatusEnum.HASERROR.getCode());
        updateById(purchase);
    }
}
