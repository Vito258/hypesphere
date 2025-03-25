package com.elon.hypesphere.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuReductionTo {
    // 销售属性ID
    private Long skuId;

    // 满减信息
    private int fullCount;
    private BigDecimal discount;

    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;
}
