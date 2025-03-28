package com.elon.hypesphere.product.controller;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.product.service.ISkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.elon.hypesphere.product.entity.SkuInfo;
import java.util.Map;

/**
 * <p>
 * sku信息 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/skuinfo")
public class SkuInfoController {

    @Autowired
    private ISkuInfoService skuInfoService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = skuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据skuId查询商品信息
     * @param skuId
     * @return
     */
    @RequestMapping("/info/{skuId}")
    public R info(@RequestParam("skuId") Long skuId) {
        SkuInfo skuInfo = skuInfoService.getById(skuId);

        return R.ok().put("skuInfo", skuInfo);
    }
}
