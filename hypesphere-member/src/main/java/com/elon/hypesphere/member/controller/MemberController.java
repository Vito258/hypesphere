package com.elon.hypesphere.member.controller;

import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.member.entity.Member;
import com.elon.hypesphere.member.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private CouponFeignService couponFeignService;

    @RequestMapping("/coupons")
    public R coupons()
    {
        Member member = new Member();
        member.setId(1L);
        R coupons = couponFeignService.memberCoupons();
        return R.ok().put("member", member).put("coupons", coupons.get("coupons"));
    }

}
