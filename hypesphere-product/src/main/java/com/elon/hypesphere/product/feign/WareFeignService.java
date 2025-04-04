package com.elon.hypesphere.product.feign;

import com.elon.hypesphere.common.to.SkuHasStockTo;
import com.elon.hypesphere.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("hypesphere-ware")
public interface WareFeignService {
    @RequestMapping("/waresku/hasstock")
    R getSkusHasStock(@RequestBody List<Long> skuIds);
}
