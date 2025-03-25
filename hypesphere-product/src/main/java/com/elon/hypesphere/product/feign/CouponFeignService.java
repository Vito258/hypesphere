package com.elon.hypesphere.product.feign;

import com.elon.hypesphere.common.to.SkuReductionTo;
import com.elon.hypesphere.common.to.SpuBoundsTo;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.product.vo.Bounds;
import com.elon.hypesphere.product.vo.Skus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("hypesphere-coupon")
public interface CouponFeignService {
    @RequestMapping("/spubounds/save")
    R saveSpuBounds(SpuBoundsTo spuBoundsTo);

    @RequestMapping("/skufullreduction/saveInfo")
    R saveSkuReduction(SkuReductionTo skuReductionTo);
}
