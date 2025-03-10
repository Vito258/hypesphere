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
 * 退货原因
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("oms_order_return_reason")
@ApiModel(value = "OrderReturnReason对象", description = "退货原因")
public class OrderReturnReason implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 退货原因名
     */
    @TableField("name")
    @ApiModelProperty("退货原因名")
    private String name;

    /**
     * 排序
     */
    @TableField("sort")
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 启用状态
     */
    @TableField("status")
    @ApiModelProperty("启用状态")
    private Boolean status;

    /**
     * create_time
     */
    @TableField("create_time")
    @ApiModelProperty("create_time")
    private LocalDateTime createTime;
}
