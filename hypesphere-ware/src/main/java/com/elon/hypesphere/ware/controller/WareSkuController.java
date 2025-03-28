package com.elon.hypesphere.ware.controller;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.ware.entity.WareSku;
import com.elon.hypesphere.ware.service.IWareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品库存 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/waresku")
public class WareSkuController {
    @Autowired
    private IWareSkuService wareSkuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        WareSku wareSku = wareSkuService.getById(id);

        return R.ok().put("wareSku", wareSku);
    }

    /**
     * 批量查询sku是否有库存
     */
//    @PostMapping("/hasstock")
//    public R getSkusHasStock(@RequestBody List<Long> skuIds) {
//        // skuId, stock
//        List<SkuHasStockTO> result = wareSkuService.getSkusHasStock(skuIds);
//
//        return R.ok().setData(result);
//    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WareSku wareSku) {
        wareSkuService.save(wareSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WareSku wareSku) {
        wareSkuService.updateById(wareSku);

        return R.ok();
    }

    /**
     * 锁定库存
     * 库存解锁的场景
     * 1）、下订单成功，订单过期没有支付被系统自动取消或者被用户手动取消，都要解锁库存
     * 2）、下订单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚。之前锁定的库存就要自动解锁
     * 3）、
     */
//    @PostMapping(value = "/lock/order")
//    public R orderLockStock(@RequestBody WareSkuLockTO lockTO) {
//        try {
//            wareSkuService.orderLockStock(lockTO);
//            return R.ok();
//        } catch (NoStockException e) {
//            return R.error(NO_STOCK_EXCEPTION.getCode(), NO_STOCK_EXCEPTION.getMsg());
//        }
//    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        wareSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
