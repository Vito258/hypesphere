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
 * 商品满减信息
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_sku_full_reduction")
@ApiModel(value = "SkuFullReduction对象", description = "商品满减信息")
public class SkuFullReduction implements Serializable {

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
     * 满多少
     */
    @ApiModelProperty("满多少")
    @TableField("full_price")
    private BigDecimal fullPrice;

    /**
     * 减多少
     */
    @ApiModelProperty("减多少")
    @TableField("reduce_price")
    private BigDecimal reducePrice;

    /**
     * 是否参与其他优惠
     */
    @TableField("add_other")
    @ApiModelProperty("是否参与其他优惠")
    private Boolean addOther;
}
