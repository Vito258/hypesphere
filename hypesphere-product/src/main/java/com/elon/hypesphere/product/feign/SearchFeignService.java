package com.elon.hypesphere.product.feign;

import com.elon.hypesphere.common.to.SkuHasStockTo;
import com.elon.hypesphere.common.to.es.SkuEsModel;
import com.elon.hypesphere.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("hypesphere-search")
public interface SearchFeignService {
    @RequestMapping("/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
