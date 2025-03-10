package com.elon.hypesphere.coupon.entity;

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
 * 优惠券信息
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_coupon")
@ApiModel(value = "Coupon对象", description = "优惠券信息")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]
     */
    @TableField("coupon_type")
    @ApiModelProperty("优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
    private Boolean couponType;

    /**
     * 优惠券图片
     */
    @TableField("coupon_img")
    @ApiModelProperty("优惠券图片")
    private String couponImg;

    /**
     * 优惠卷名字
     */
    @ApiModelProperty("优惠卷名字")
    @TableField("coupon_name")
    private String couponName;

    /**
     * 数量
     */
    @TableField("num")
    @ApiModelProperty("数量")
    private Integer num;

    /**
     * 金额
     */
    @TableField("amount")
    @ApiModelProperty("金额")
    private BigDecimal amount;

    /**
     * 每人限领张数
     */
    @TableField("per_limit")
    @ApiModelProperty("每人限领张数")
    private Integer perLimit;

    /**
     * 使用门槛
     */
    @TableField("min_point")
    @ApiModelProperty("使用门槛")
    private BigDecimal minPoint;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    /**
     * 使用类型[0->全场通用；1->指定分类；2->指定商品]
     */
    @TableField("use_type")
    @ApiModelProperty("使用类型[0->全场通用；1->指定分类；2->指定商品]")
    private Boolean useType;

    /**
     * 备注
     */
    @TableField("note")
    @ApiModelProperty("备注")
    private String note;

    /**
     * 发行数量
     */
    @ApiModelProperty("发行数量")
    @TableField("publish_count")
    private Integer publishCount;

    /**
     * 已使用数量
     */
    @TableField("use_count")
    @ApiModelProperty("已使用数量")
    private Integer useCount;

    /**
     * 领取数量
     */
    @ApiModelProperty("领取数量")
    @TableField("receive_count")
    private Integer receiveCount;

    /**
     * 可以领取的开始日期
     */
    @ApiModelProperty("可以领取的开始日期")
    @TableField("enable_start_time")
    private LocalDateTime enableStartTime;

    /**
     * 可以领取的结束日期
     */
    @ApiModelProperty("可以领取的结束日期")
    @TableField("enable_end_time")
    private LocalDateTime enableEndTime;

    /**
     * 优惠码
     */
    @TableField("code")
    @ApiModelProperty("优惠码")
    private String code;

    /**
     * 可以领取的会员等级[0->不限等级，其他-对应等级]
     */
    @TableField("member_level")
    @ApiModelProperty("可以领取的会员等级[0->不限等级，其他-对应等级]")
    private Boolean memberLevel;

    /**
     * 发布状态[0-未发布，1-已发布]
     */
    @TableField("publish")
    @ApiModelProperty("发布状态[0-未发布，1-已发布]")
    private Boolean publish;
}
