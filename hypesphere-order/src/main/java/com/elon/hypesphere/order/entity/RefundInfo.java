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
 * 退款信息
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("oms_refund_info")
@ApiModel(value = "RefundInfo对象", description = "退款信息")
public class RefundInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 退款的订单
     */
    @ApiModelProperty("退款的订单")
    @TableField("order_return_id")
    private Long orderReturnId;

    /**
     * 退款金额
     */
    @TableField("refund")
    @ApiModelProperty("退款金额")
    private BigDecimal refund;

    /**
     * 退款交易流水号
     */
    @TableField("refund_sn")
    @ApiModelProperty("退款交易流水号")
    private String refundSn;

    /**
     * 退款状态
     */
    @ApiModelProperty("退款状态")
    @TableField("refund_status")
    private Boolean refundStatus;

    /**
     * 退款渠道[1-支付宝，2-微信，3-银联，4-汇款]
     */
    @TableField("refund_channel")
    @ApiModelProperty("退款渠道[1-支付宝，2-微信，3-银联，4-汇款]")
    private Byte refundChannel;

    @TableField("refund_content")
    private String refundContent;
}
