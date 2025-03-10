package com.elon.hypesphere.member.entity;

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
 * 成长值变化历史记录
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("ums_growth_change_history")
@ApiModel(value = "GrowthChangeHistory对象", description = "成长值变化历史记录")
public class GrowthChangeHistory implements Serializable {

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
     * create_time
     */
    @TableField("create_time")
    @ApiModelProperty("create_time")
    private LocalDateTime createTime;

    /**
     * 改变的值（正负计数）
     */
    @TableField("change_count")
    @ApiModelProperty("改变的值（正负计数）")
    private Integer changeCount;

    /**
     * 备注
     */
    @TableField("note")
    @ApiModelProperty("备注")
    private String note;

    /**
     * 积分来源[0-购物，1-管理员修改]
     */
    @TableField("source_type")
    @ApiModelProperty("积分来源[0-购物，1-管理员修改]")
    private Byte sourceType;
}
