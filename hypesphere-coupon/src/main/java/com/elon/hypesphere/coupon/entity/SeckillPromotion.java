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
 * 秒杀活动
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_seckill_promotion")
@ApiModel(value = "SeckillPromotion对象", description = "秒杀活动")
public class SeckillPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动标题
     */
    @TableField("title")
    @ApiModelProperty("活动标题")
    private String title;

    /**
     * 开始日期
     */
    @ApiModelProperty("开始日期")
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 结束日期
     */
    @TableField("end_time")
    @ApiModelProperty("结束日期")
    private LocalDateTime endTime;

    /**
     * 上下线状态
     */
    @TableField("status")
    @ApiModelProperty("上下线状态")
    private Byte status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField("user_id")
    @ApiModelProperty("创建人")
    private Long userId;
}
