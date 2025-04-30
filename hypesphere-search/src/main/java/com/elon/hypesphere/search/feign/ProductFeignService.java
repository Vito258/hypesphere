package com.elon.hypesphere.search.feign;

import com.elon.hypesphere.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("hypesphere-product")
public interface ProductFeignService {
    @RequestMapping("/attr/info/{attrId}")
    R getAttrsInfo(@PathVariable("attrId") Long attrId);

    @RequestMapping("/brand/infos")
    R brandsInfo(@RequestParam("brandIds") List<Long> brandIds);
}
