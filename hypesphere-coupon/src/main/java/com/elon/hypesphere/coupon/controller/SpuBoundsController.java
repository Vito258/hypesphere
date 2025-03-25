package com.elon.hypesphere.coupon.controller;

import com.elon.hypesphere.common.to.SpuBoundsTo;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.coupon.entity.SpuBounds;
import com.elon.hypesphere.coupon.service.ISpuBoundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品spu积分设置 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/spubounds")
public class SpuBoundsController {
    @Autowired
    private ISpuBoundsService spuBoundsService;

    /**
     * 保存功能
     */
    @RequestMapping("/save")
    public R save(@RequestBody SpuBounds bounds) {
        spuBoundsService.save(bounds);
        return R.ok();
    }

}
