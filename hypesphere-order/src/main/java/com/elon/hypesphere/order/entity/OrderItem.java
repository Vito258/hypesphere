package com.elon.hypesphere.order.entity;

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
 * 订单项信息
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("oms_order_item")
@ApiModel(value = "OrderItem对象", description = "订单项信息")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * order_id
     */
    @TableField("order_id")
    @ApiModelProperty("order_id")
    private Long orderId;

    /**
     * order_sn
     */
    @TableField("order_sn")
    @ApiModelProperty("order_sn")
    private String orderSn;

    /**
     * spu_id
     */
    @TableField("spu_id")
    @ApiModelProperty("spu_id")
    private Long spuId;

    /**
     * spu_name
     */
    @TableField("spu_name")
    @ApiModelProperty("spu_name")
    private String spuName;

    /**
     * spu_pic
     */
    @TableField("spu_pic")
    @ApiModelProperty("spu_pic")
    private String spuPic;

    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    @TableField("spu_brand")
    private String spuBrand;

    /**
     * 商品分类id
     */
    @TableField("category_id")
    @ApiModelProperty("商品分类id")
    private Long categoryId;

    /**
     * 商品sku编号
     */
    @TableField("sku_id")
    @ApiModelProperty("商品sku编号")
    private Long skuId;

    /**
     * 商品sku名字
     */
    @TableField("sku_name")
    @ApiModelProperty("商品sku名字")
    private String skuName;

    /**
     * 商品sku图片
     */
    @TableField("sku_pic")
    @ApiModelProperty("商品sku图片")
    private String skuPic;

    /**
     * 商品sku价格
     */
    @TableField("sku_price")
    @ApiModelProperty("商品sku价格")
    private BigDecimal skuPrice;

    /**
     * 商品购买的数量
     */
    @TableField("sku_quantity")
    @ApiModelProperty("商品购买的数量")
    private Integer skuQuantity;

    /**
     * 商品销售属性组合（JSON）
     */
    @TableField("sku_attrs_vals")
    @ApiModelProperty("商品销售属性组合（JSON）")
    private String skuAttrsVals;

    /**
     * 商品促销分解金额
     */
    @ApiModelProperty("商品促销分解金额")
    @TableField("promotion_amount")
    private BigDecimal promotionAmount;

    /**
     * 优惠券优惠分解金额
     */
    @TableField("coupon_amount")
    @ApiModelProperty("优惠券优惠分解金额")
    private BigDecimal couponAmount;

    /**
     * 积分优惠分解金额
     */
    @ApiModelProperty("积分优惠分解金额")
    @TableField("integration_amount")
    private BigDecimal integrationAmount;

    /**
     * 该商品经过优惠后的分解金额
     */
    @TableField("real_amount")
    @ApiModelProperty("该商品经过优惠后的分解金额")
    private BigDecimal realAmount;

    /**
     * 赠送积分
     */
    @ApiModelProperty("赠送积分")
    @TableField("gift_integration")
    private Integer giftIntegration;

    /**
     * 赠送成长值
     */
    @ApiModelProperty("赠送成长值")
    @TableField("gift_growth")
    private Integer giftGrowth;
}
