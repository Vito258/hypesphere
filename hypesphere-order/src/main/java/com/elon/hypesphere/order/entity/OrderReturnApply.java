package com.elon.hypesphere.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 订单退货申请
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("oms_order_return_apply")
@ApiModel(value = "OrderReturnApply对象", description = "订单退货申请")
public class OrderReturnApply implements Serializable {

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
     * 退货商品id
     */
    @TableField("sku_id")
    @ApiModelProperty("退货商品id")
    private Long skuId;

    /**
     * 订单编号
     */
    @TableField("order_sn")
    @ApiModelProperty("订单编号")
    private String orderSn;

    /**
     * 申请时间
     */
    @ApiModelProperty("申请时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 会员用户名
     */
    @ApiModelProperty("会员用户名")
    @TableField("member_username")
    private String memberUsername;

    /**
     * 退款金额
     */
    @ApiModelProperty("退款金额")
    @TableField("return_amount")
    private BigDecimal returnAmount;

    /**
     * 退货人姓名
     */
    @ApiModelProperty("退货人姓名")
    @TableField("return_name")
    private String returnName;

    /**
     * 退货人电话
     */
    @ApiModelProperty("退货人电话")
    @TableField("return_phone")
    private String returnPhone;

    /**
     * 申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]
     */
    @TableField("status")
    @ApiModelProperty("申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]")
    private Boolean status;

    /**
     * 处理时间
     */
    @ApiModelProperty("处理时间")
    @TableField("handle_time")
    private LocalDateTime handleTime;

    /**
     * 商品图片
     */
    @TableField("sku_img")
    @ApiModelProperty("商品图片")
    private String skuImg;

    /**
     * 商品名称
     */
    @TableField("sku_name")
    @ApiModelProperty("商品名称")
    private String skuName;

    /**
     * 商品品牌
     */
    @TableField("sku_brand")
    @ApiModelProperty("商品品牌")
    private String skuBrand;

    /**
     * 商品销售属性(JSON)
     */
    @TableField("sku_attrs_vals")
    @ApiModelProperty("商品销售属性(JSON)")
    private String skuAttrsVals;

    /**
     * 退货数量
     */
    @TableField("sku_count")
    @ApiModelProperty("退货数量")
    private Integer skuCount;

    /**
     * 商品单价
     */
    @TableField("sku_price")
    @ApiModelProperty("商品单价")
    private BigDecimal skuPrice;

    /**
     * 商品实际支付单价
     */
    @ApiModelProperty("商品实际支付单价")
    @TableField("sku_real_price")
    private BigDecimal skuRealPrice;

    /**
     * 原因
     */
    @TableField("reason")
    @ApiModelProperty("原因")
    private String reason;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @TableField("description述")
    private String description述;

    /**
     * 凭证图片，以逗号隔开
     */
    @TableField("desc_pics")
    @ApiModelProperty("凭证图片，以逗号隔开")
    private String descPics;

    /**
     * 处理备注
     */
    @ApiModelProperty("处理备注")
    @TableField("handle_note")
    private String handleNote;

    /**
     * 处理人员
     */
    @ApiModelProperty("处理人员")
    @TableField("handle_man")
    private String handleMan;

    /**
     * 收货人
     */
    @ApiModelProperty("收货人")
    @TableField("receive_man")
    private String receiveMan;

    /**
     * 收货时间
     */
    @ApiModelProperty("收货时间")
    @TableField("receive_time")
    private LocalDateTime receiveTime;

    /**
     * 收货备注
     */
    @ApiModelProperty("收货备注")
    @TableField("receive_note")
    private String receiveNote;

    /**
     * 收货电话
     */
    @ApiModelProperty("收货电话")
    @TableField("receive_phone")
    private String receivePhone;

    /**
     * 公司收货地址
     */
    @ApiModelProperty("公司收货地址")
    @TableField("company_address")
    private String companyAddress;
}
