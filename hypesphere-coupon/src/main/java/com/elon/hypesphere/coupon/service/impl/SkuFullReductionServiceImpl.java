package com.elon.hypesphere.coupon.service.impl;

import com.elon.hypesphere.common.to.MemberPrice;
import com.elon.hypesphere.common.to.SkuReductionTo;
import com.elon.hypesphere.coupon.entity.SkuFullReduction;
import com.elon.hypesphere.coupon.entity.SkuLadder;
import com.elon.hypesphere.coupon.mapper.SkuFullReductionMapper;
import com.elon.hypesphere.coupon.service.IMemberPriceService;
import com.elon.hypesphere.coupon.service.ISkuFullReductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elon.hypesphere.coupon.service.ISkuLadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品满减信息 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper, SkuFullReduction> implements ISkuFullReductionService {

    @Autowired
    private ISkuLadderService skuLadderService;

    @Autowired
    private IMemberPriceService memberPriceService;

    /**
     * 保存sku满减信息
     *
     * @param skuReductionTo
     */
    @Override
    public void saveSkuReduction(SkuReductionTo skuReductionTo) {
        // 1、保存满减打折, sms_sku_ladder
        SkuLadder skuLadder = new SkuLadder();
        skuLadder.setSkuId(skuReductionTo.getSkuId());
        skuLadder.setFullCount(skuReductionTo.getFullCount());
        skuLadder.setDiscount(skuReductionTo.getDiscount());
        skuLadder.setAddOther(skuReductionTo.getCountStatus() != 0);
        if (skuLadder.getFullCount() > 0) {
            skuLadderService.save(skuLadder);
        }


        // 2、保存满减信息, sms_sku_full_reduction
        SkuFullReduction skuFullReduction = new SkuFullReduction();
        skuFullReduction.setAddOther(skuReductionTo.getPriceStatus() != 0);
        skuFullReduction.setFullPrice(skuReductionTo.getFullPrice());
        skuFullReduction.setReducePrice(skuReductionTo.getReducePrice());
        skuFullReduction.setSkuId(skuReductionTo.getSkuId());
        if(skuFullReduction.getFullPrice().compareTo(BigDecimal.ZERO) == 1){
            save(skuFullReduction);
        }

        // 3、保存会员价格, sms_member_price
        List<MemberPrice> memberPrice = skuReductionTo.getMemberPrice();
        List<com.elon.hypesphere.coupon.entity.MemberPrice> memPrices = List.of();
        if (memberPrice != null && memberPrice.size() > 0) {
            memPrices = memberPrice.stream().map(item -> {
                com.elon.hypesphere.coupon.entity.MemberPrice priceEntity = new com.elon.hypesphere.coupon.entity.MemberPrice();
                priceEntity.setSkuId(skuReductionTo.getSkuId());
                priceEntity.setMemberLevelId(item.getId());
                priceEntity.setMemberLevelName(item.getName());
                priceEntity.setMemberPrice(item.getPrice());
                priceEntity.setAddOther(true);
                return priceEntity;
            }).filter(item -> item.getMemberPrice().compareTo(BigDecimal.ZERO) == 1).collect(Collectors.toList());
        }
        memberPriceService.saveBatch(memPrices);
    }
}
