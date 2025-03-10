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
 * 订单
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("oms_order")
@ApiModel(value = "Order对象", description = "订单")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * member_id
     */
    @TableField("member_id")
    @ApiModelProperty("member_id")
    private Long memberId;

    /**
     * 订单号
     */
    @TableField("order_sn")
    @ApiModelProperty("订单号")
    private String orderSn;

    /**
     * 使用的优惠券
     */
    @TableField("coupon_id")
    @ApiModelProperty("使用的优惠券")
    private Long couponId;

    /**
     * create_time
     */
    @TableField("create_time")
    @ApiModelProperty("create_time")
    private LocalDateTime createTime;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @TableField("member_username")
    private String memberUsername;

    /**
     * 订单总额
     */
    @ApiModelProperty("订单总额")
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 应付总额
     */
    @ApiModelProperty("应付总额")
    @TableField("pay_amount")
    private BigDecimal payAmount;

    /**
     * 运费金额
     */
    @ApiModelProperty("运费金额")
    @TableField("freight_amount")
    private BigDecimal freightAmount;

    /**
     * 促销优化金额（促销价、满减、阶梯价）
     */
    @TableField("promotion_amount")
    @ApiModelProperty("促销优化金额（促销价、满减、阶梯价）")
    private BigDecimal promotionAmount;

    /**
     * 积分抵扣金额
     */
    @ApiModelProperty("积分抵扣金额")
    @TableField("integration_amount")
    private BigDecimal integrationAmount;

    /**
     * 优惠券抵扣金额
     */
    @ApiModelProperty("优惠券抵扣金额")
    @TableField("coupon_amount")
    private BigDecimal couponAmount;

    /**
     * 后台调整订单使用的折扣金额
     */
    @TableField("discount_amount")
    @ApiModelProperty("后台调整订单使用的折扣金额")
    private BigDecimal discountAmount;

    /**
     * 支付方式【PAYPAL中国->101；PAYPAL香港->102；PAYPAL全球->103；支付宝->201；支付宝香港->202；支付宝全球->203；微信->301；微信香港->302；微信全球->303；银联->401；货到付款->501】
     */
    @TableField("pay_type")
    @ApiModelProperty("支付方式【PAYPAL中国->101；PAYPAL香港->102；PAYPAL全球->103；支付宝->201；支付宝香港->202；支付宝全球->203；微信->301；微信香港->302；微信全球->303；银联->401；货到付款->501】")
    private Integer payType;

    /**
     * 订单来源[0->PC订单；1->app订单]
     */
    @TableField("source_type")
    @ApiModelProperty("订单来源[0->PC订单；1->app订单]")
    private Byte sourceType;

    /**
     * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
     */
    @TableField("status")
    @ApiModelProperty("订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】")
    private Byte status;

    /**
     * 物流公司(配送方式)
     */
    @ApiModelProperty("物流公司(配送方式)")
    @TableField("delivery_company")
    private String deliveryCompany;

    /**
     * 物流单号
     */
    @ApiModelProperty("物流单号")
    @TableField("delivery_sn")
    private String deliverySn;

    /**
     * 自动确认时间（天）
     */
    @ApiModelProperty("自动确认时间（天）")
    @TableField("auto_confirm_day")
    private Integer autoConfirmDay;

    /**
     * 可以获得的积分
     */
    @TableField("integration")
    @ApiModelProperty("可以获得的积分")
    private Integer integration;

    /**
     * 可以获得的成长值
     */
    @TableField("growth")
    @ApiModelProperty("可以获得的成长值")
    private Integer growth;

    /**
     * 发票类型[0->不开发票；1->电子发票；2->纸质发票]
     */
    @TableField("bill_type")
    @ApiModelProperty("发票类型[0->不开发票；1->电子发票；2->纸质发票]")
    private Byte billType;

    /**
     * 发票抬头
     */
    @ApiModelProperty("发票抬头")
    @TableField("bill_header")
    private String billHeader;

    /**
     * 发票内容
     */
    @ApiModelProperty("发票内容")
    @TableField("bill_content")
    private String billContent;

    /**
     * 收票人电话
     */
    @ApiModelProperty("收票人电话")
    @TableField("bill_receiver_phone")
    private String billReceiverPhone;

    /**
     * 收票人邮箱
     */
    @ApiModelProperty("收票人邮箱")
    @TableField("bill_receiver_email")
    private String billReceiverEmail;

    /**
     * 收货人姓名
     */
    @ApiModelProperty("收货人姓名")
    @TableField("receiver_name")
    private String receiverName;

    /**
     * 收货人电话
     */
    @ApiModelProperty("收货人电话")
    @TableField("receiver_phone")
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    @ApiModelProperty("收货人邮编")
    @TableField("receiver_post_code")
    private String receiverPostCode;

    /**
     * 省份/直辖市
     */
    @ApiModelProperty("省份/直辖市")
    @TableField("receiver_province")
    private String receiverProvince;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    @TableField("receiver_city")
    private String receiverCity;

    /**
     * 区
     */
    @ApiModelProperty("区")
    @TableField("receiver_region")
    private String receiverRegion;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    @TableField("receiver_detail_address")
    private String receiverDetailAddress;

    /**
     * 订单备注
     */
    @TableField("note")
    @ApiModelProperty("订单备注")
    private String note;

    /**
     * 确认收货状态[0->未确认；1->已确认]
     */
    @TableField("confirm_status")
    @ApiModelProperty("确认收货状态[0->未确认；1->已确认]")
    private Byte confirmStatus;

    /**
     * 删除状态【0->未删除；1->已删除】
     */
    @TableField("delete_status")
    @ApiModelProperty("删除状态【0->未删除；1->已删除】")
    private Byte deleteStatus;

    /**
     * 下单时使用的积分
     */
    @ApiModelProperty("下单时使用的积分")
    @TableField("use_integration")
    private Integer useIntegration;

    /**
     * 支付时间
     */
    @ApiModelProperty("支付时间")
    @TableField("payment_time")
    private LocalDateTime paymentTime;

    /**
     * 发货时间
     */
    @ApiModelProperty("发货时间")
    @TableField("delivery_time")
    private LocalDateTime deliveryTime;

    /**
     * 确认收货时间
     */
    @ApiModelProperty("确认收货时间")
    @TableField("receive_time")
    private LocalDateTime receiveTime;

    /**
     * 评价时间
     */
    @ApiModelProperty("评价时间")
    @TableField("comment_time")
    private LocalDateTime commentTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField("modify_time")
    private LocalDateTime modifyTime;
}
