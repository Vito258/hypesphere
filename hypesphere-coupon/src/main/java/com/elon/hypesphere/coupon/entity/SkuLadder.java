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
 * 商品阶梯价格
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_sku_ladder")
@ApiModel(value = "SkuLadder对象", description = "商品阶梯价格")
public class SkuLadder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * spu_id
     */
    @TableField("sku_id")
    @ApiModelProperty("spu_id")
    private Long skuId;

    /**
     * 满几件
     */
    @ApiModelProperty("满几件")
    @TableField("full_count")
    private Integer fullCount;

    /**
     * 打几折
     */
    @TableField("discount")
    @ApiModelProperty("打几折")
    private BigDecimal discount;

    /**
     * 折后价
     */
    @TableField("price")
    @ApiModelProperty("折后价")
    private BigDecimal price;

    /**
     * 是否叠加其他优惠[0-不可叠加，1-可叠加]
     */
    @TableField("add_other")
    @ApiModelProperty("是否叠加其他优惠[0-不可叠加，1-可叠加]")
    private Boolean addOther;
}
