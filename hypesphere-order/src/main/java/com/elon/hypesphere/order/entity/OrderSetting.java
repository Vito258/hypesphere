package com.elon.hypesphere.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 订单配置信息
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("oms_order_setting")
@ApiModel(value = "OrderSetting对象", description = "订单配置信息")
public class OrderSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 秒杀订单超时关闭时间(分)
     */
    @ApiModelProperty("秒杀订单超时关闭时间(分)")
    @TableField("flash_order_overtime")
    private Integer flashOrderOvertime;

    /**
     * 正常订单超时时间(分)
     */
    @ApiModelProperty("正常订单超时时间(分)")
    @TableField("normal_order_overtime")
    private Integer normalOrderOvertime;

    /**
     * 发货后自动确认收货时间（天）
     */
    @TableField("confirm_overtime")
    @ApiModelProperty("发货后自动确认收货时间（天）")
    private Integer confirmOvertime;

    /**
     * 自动完成交易时间，不能申请退货（天）
     */
    @TableField("finish_overtime")
    @ApiModelProperty("自动完成交易时间，不能申请退货（天）")
    private Integer finishOvertime;

    /**
     * 订单完成后自动好评时间（天）
     */
    @TableField("comment_overtime")
    @ApiModelProperty("订单完成后自动好评时间（天）")
    private Integer commentOvertime;

    /**
     * 会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】
     */
    @TableField("member_level")
    @ApiModelProperty("会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】")
    private Byte memberLevel;
}
