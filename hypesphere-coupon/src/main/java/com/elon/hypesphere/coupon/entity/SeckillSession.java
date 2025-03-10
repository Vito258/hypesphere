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
 * 秒杀活动场次
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_seckill_session")
@ApiModel(value = "SeckillSession对象", description = "秒杀活动场次")
public class SeckillSession implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 场次名称
     */
    @TableField("name")
    @ApiModelProperty("场次名称")
    private String name;

    /**
     * 每日开始时间
     */
    @TableField("start_time")
    @ApiModelProperty("每日开始时间")
    private LocalDateTime startTime;

    /**
     * 每日结束时间
     */
    @TableField("end_time")
    @ApiModelProperty("每日结束时间")
    private LocalDateTime endTime;

    /**
     * 启用状态
     */
    @TableField("status")
    @ApiModelProperty("启用状态")
    private Boolean status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}
