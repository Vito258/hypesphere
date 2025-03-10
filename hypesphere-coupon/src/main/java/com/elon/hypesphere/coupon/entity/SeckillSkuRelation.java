package com.elon.hypesphere.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 秒杀活动商品关联
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_seckill_sku_relation")
@ApiModel(value = "SeckillSkuRelation对象", description = "秒杀活动商品关联")
public class SeckillSkuRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动id
     */
    @ApiModelProperty("活动id")
    @TableField("promotion_id")
    private Long promotionId;

    /**
     * 活动场次id
     */
    @ApiModelProperty("活动场次id")
    @TableField("promotion_session_id")
    private Long promotionSessionId;

    /**
     * 商品id
     */
    @TableField("sku_id")
    @ApiModelProperty("商品id")
    private Long skuId;

    /**
     * 秒杀价格
     */
    @ApiModelProperty("秒杀价格")
    @TableField("seckill_price")
    private BigDecimal seckillPrice;

    /**
     * 秒杀总量
     */
    @ApiModelProperty("秒杀总量")
    @TableField("seckill_count")
    private Integer seckillCount;

    /**
     * 每人限购数量
     */
    @ApiModelProperty("每人限购数量")
    @TableField("seckill_limit")
    private Integer seckillLimit;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    @TableField("seckill_sort")
    private Integer seckillSort;
}
