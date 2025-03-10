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
 * 支付信息表
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("oms_payment_info")
@ApiModel(value = "PaymentInfo对象", description = "支付信息表")
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号（对外业务号）
     */
    @TableField("order_sn")
    @ApiModelProperty("订单号（对外业务号）")
    private String orderSn;

    /**
     * 订单id
     */
    @TableField("order_id")
    @ApiModelProperty("订单id")
    private Long orderId;

    /**
     * 支付宝交易流水号
     */
    @ApiModelProperty("支付宝交易流水号")
    @TableField("alipay_trade_no")
    private String alipayTradeNo;

    /**
     * 支付总金额
     */
    @ApiModelProperty("支付总金额")
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 交易内容
     */
    @TableField("subject")
    @ApiModelProperty("交易内容")
    private String subject;

    /**
     * 支付状态
     */
    @ApiModelProperty("支付状态")
    @TableField("payment_status")
    private String paymentStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 确认时间
     */
    @ApiModelProperty("确认时间")
    @TableField("confirm_time")
    private LocalDateTime confirmTime;

    /**
     * 回调内容
     */
    @ApiModelProperty("回调内容")
    @TableField("callback_content")
    private String callbackContent;

    /**
     * 回调时间
     */
    @ApiModelProperty("回调时间")
    @TableField("callback_time")
    private LocalDateTime callbackTime;
}
