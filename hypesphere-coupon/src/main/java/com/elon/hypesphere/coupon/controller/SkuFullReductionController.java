package com.elon.hypesphere.coupon.controller;

import com.elon.hypesphere.common.to.SkuReductionTo;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.coupon.service.ISkuFullReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品满减信息 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/skufullreduction")
public class SkuFullReductionController {
   @Autowired
   private ISkuFullReductionService skuFullReductionService;

   @RequestMapping("/saveInfo")
   public R saveSkuReduction(@RequestBody  SkuReductionTo skuReductionTo) {
      skuFullReductionService.saveSkuReduction(skuReductionTo);
      return R.ok();
   }
}
