package com.elon.hypesphere.search.service;

import com.elon.hypesphere.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {
    // 保存skuEsModel到es
    boolean productStatusUp(List<SkuEsModel> skuEsModels);
}
