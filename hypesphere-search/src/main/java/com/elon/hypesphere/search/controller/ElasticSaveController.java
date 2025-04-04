package com.elon.hypesphere.search.controller;

import com.elon.hypesphere.common.exception.BizCodeEnume;
import com.elon.hypesphere.common.to.es.SkuEsModel;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.search.service.ProductSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/save")
public class ElasticSaveController {

  @Autowired
  private ProductSaveService productSaveService;

  @RequestMapping("/product")
  public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
    boolean b = productSaveService.productStatusUp(skuEsModels);
    if(b){
      return R.ok();
    }else{
      return R.error(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
    }
  }
}
