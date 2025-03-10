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
 * 秒杀商品通知订阅
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_seckill_sku_notice")
@ApiModel(value = "SeckillSkuNotice对象", description = "秒杀商品通知订阅")
public class SeckillSkuNotice implements Serializable {

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
     * sku_id
     */
    @TableField("sku_id")
    @ApiModelProperty("sku_id")
    private Long skuId;

    /**
     * 活动场次id
     */
    @TableField("session_id")
    @ApiModelProperty("活动场次id")
    private Long sessionId;

    /**
     * 订阅时间
     */
    @ApiModelProperty("订阅时间")
    @TableField("subcribe_time")
    private LocalDateTime subcribeTime;

    /**
     * 发送时间
     */
    @TableField("send_time")
    @ApiModelProperty("发送时间")
    private LocalDateTime sendTime;

    /**
     * 通知方式[0-短信，1-邮件]
     */
    @TableField("notice_type")
    @ApiModelProperty("通知方式[0-短信，1-邮件]")
    private Boolean noticeType;
}
