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
 * 首页轮播广告
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_home_adv")
@ApiModel(value = "HomeAdv对象", description = "首页轮播广告")
public class HomeAdv implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名字
     */
    @TableField("name")
    @ApiModelProperty("名字")
    private String name;

    /**
     * 图片地址
     */
    @TableField("pic")
    @ApiModelProperty("图片地址")
    private String pic;

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
     * 状态
     */
    @TableField("status")
    @ApiModelProperty("状态")
    private Boolean status;

    /**
     * 点击数
     */
    @ApiModelProperty("点击数")
    @TableField("click_count")
    private Integer clickCount;

    /**
     * 广告详情连接地址
     */
    @TableField("url")
    @ApiModelProperty("广告详情连接地址")
    private String url;

    /**
     * 备注
     */
    @TableField("note")
    @ApiModelProperty("备注")
    private String note;

    /**
     * 排序
     */
    @TableField("sort")
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 发布者
     */
    @ApiModelProperty("发布者")
    @TableField("publisher_id")
    private Long publisherId;

    /**
     * 审核者
     */
    @TableField("auth_id")
    @ApiModelProperty("审核者")
    private Long authId;
}
