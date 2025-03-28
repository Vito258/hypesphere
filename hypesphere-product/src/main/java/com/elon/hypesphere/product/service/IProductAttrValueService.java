package com.elon.hypesphere.product.service;

import com.elon.hypesphere.product.entity.ProductAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IProductAttrValueService extends IService<ProductAttrValue> {

    // 获取spu规格，前端回显商品规格参数值
    List<ProductAttrValue> baseAttrlistforspu(Long spuId);

    // 修改商品规格参数值
    void updateSpuAttr(Long spuId, List<ProductAttrValue> entities);
}
