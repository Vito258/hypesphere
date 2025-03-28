package com.elon.hypesphere.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.Query;
import com.elon.hypesphere.ware.entity.Purchase;
import com.elon.hypesphere.ware.entity.PurchaseDetail;
import com.elon.hypesphere.ware.mapper.PurchaseDetailMapper;
import com.elon.hypesphere.ware.service.IPurchaseDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>
 * 采购需求 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetail> implements IPurchaseDetailService {
    /**
     * 商品库存（可根据skuId、wareId查找）
     */
     @Override
    public PageUtils queryPage(Map<String, Object> params) {
        // key：采购单id  or  skuid
        // status：0 采购需求状态
        // wareId：1 仓库Id
        QueryWrapper<PurchaseDetail> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and(w->{
                w.eq("purchase_id", key).or().eq("sku_id", key);
            });
        }

        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }

        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(wareId)) {
            queryWrapper.eq("ware_id", wareId);
        }

        IPage<PurchaseDetail> page = this.page(
                new Query<PurchaseDetail>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }
}
