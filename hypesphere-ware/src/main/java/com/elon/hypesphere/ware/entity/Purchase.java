package com.elon.hypesphere.ware.entity;

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
 * 采购单
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("wms_purchase")
@ApiModel(value = "Purchase对象", description = "采购单")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("assignee_id")
    private Long assigneeId;

    @TableField("assignee_name")
    private String assigneeName;

    @TableField("phone")
    private String phone;

    @TableField("priority")
    private Integer priority;

    @TableField("status")
    private Integer status;

    @TableField("ware_id")
    private Long wareId;

    @TableField("amount")
    private BigDecimal amount;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
