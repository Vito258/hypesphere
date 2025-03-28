package com.elon.hypesphere.ware.feign;

import com.elon.hypesphere.common.to.SkuReductionTo;
import com.elon.hypesphere.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("hypesphere-product")
public interface ProductFeignService {
    @RequestMapping("/skuinfo/info/{skuId}")
    R getSkuInfoBySkuId(@RequestParam("skuId") Long skuId);
}
