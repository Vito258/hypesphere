package com.elon.hypesphere.member.feign;

import com.elon.hypesphere.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. @FeignClient("hypesphere-coupon")：指定远程调用的微服务名，即服务提供者
// 2. @RequestMapping("/member/list")：指定远程调用的微服务接口路径，即服务提供者接口路径
// 3. public R memberCoupons()：指定远程调用的微服务接口方法，即服务提供者接口方法

@FeignClient(name = "hypesphere-coupon")
public interface CouponFeignService {
    @RequestMapping("/coupon/member/list")
    public R memberCoupons();
}
