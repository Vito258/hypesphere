package com.elon.hypesphere.coupon.controller;

import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.coupon.entity.Coupon;
import com.elon.hypesphere.coupon.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RefreshScope
@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private ICouponService couponService;

    @Value("${coupon.user.name}")
    private String username;


    @RequestMapping("/member/list")
    public R memberCoupons() {
        Coupon coupon = new Coupon();
        coupon.setCouponName("满200 减30");
        return  R.ok().put("coupons", Collections.singletonList(coupon));
    }

    @RequestMapping("test")
    public R test() {
        return R.ok().put("username", username);
    }

}
