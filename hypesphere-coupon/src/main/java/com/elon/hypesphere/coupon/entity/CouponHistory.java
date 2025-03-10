package com.elon.hypesphere.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 优惠券领取历史记录
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_coupon_history")
@ApiModel(value = "CouponHistory对象", description = "优惠券领取历史记录")
public class CouponHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 优惠券id
     */
    @TableField("coupon_id")
    @ApiModelProperty("优惠券id")
    private Long couponId;

    /**
     * 会员id
     */
    @TableField("member_id")
    @ApiModelProperty("会员id")
    private Long memberId;

    /**
     * 会员名字
     */
    @ApiModelProperty("会员名字")
    @TableField("member_nick_name")
    private String memberNickName;

    /**
     * 获取方式[0->后台赠送；1->主动领取]
     */
    @TableField("get_type")
    @ApiModelProperty("获取方式[0->后台赠送；1->主动领取]")
    private Boolean getType;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 使用状态[0->未使用；1->已使用；2->已过期]
     */
    @TableField("use_type")
    @ApiModelProperty("使用状态[0->未使用；1->已使用；2->已过期]")
    private Boolean useType;

    /**
     * 使用时间
     */
    @TableField("use_time")
    @ApiModelProperty("使用时间")
    private LocalDateTime useTime;

    /**
     * 订单id
     */
    @TableField("order_id")
    @ApiModelProperty("订单id")
    private Long orderId;

    /**
     * 订单号
     */
    @TableField("order_sn")
    @ApiModelProperty("订单号")
    private Long orderSn;
}
