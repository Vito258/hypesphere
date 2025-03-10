package com.elon.hypesphere.order.entity;

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
 * 
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("undo_log")
@ApiModel(value = "Log对象", description = "")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("branch_id")
    private Long branchId;

    @TableField("xid")
    private String xid;

    @TableField("context")
    private String context;

    @TableField("rollback_info")
    private byte[] rollbackInfo;

    @TableField("log_status")
    private Integer logStatus;

    @TableField("log_created")
    private LocalDateTime logCreated;

    @TableField("log_modified")
    private LocalDateTime logModified;

    @TableField("ext")
    private String ext;
}
