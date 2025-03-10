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
 * 积分变化历史记录
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("ums_integration_change_history")
@ApiModel(value = "IntegrationChangeHistory对象", description = "积分变化历史记录")
public class IntegrationChangeHistory implements Serializable {

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
     * 变化的值
     */
    @ApiModelProperty("变化的值")
    @TableField("change_count")
    private Integer changeCount;

    /**
     * 备注
     */
    @TableField("note")
    @ApiModelProperty("备注")
    private String note;

    /**
     * 来源[0->购物；1->管理员修改;2->活动]
     */
    @TableField("source_tyoe")
    @ApiModelProperty("来源[0->购物；1->管理员修改;2->活动]")
    private Byte sourceTyoe;
}
