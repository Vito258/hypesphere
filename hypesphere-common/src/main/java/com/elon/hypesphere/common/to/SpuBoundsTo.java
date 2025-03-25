package com.elon.hypesphere.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * TO 用于服务之间的数据传输，A调用B时将数据以TO的形式返回给B
 */
@Data
public class SpuBoundsTo {
    private Long spuId;

    // 积分信息
    private BigDecimal buyBounds;
    private BigDecimal growBounds;

}
