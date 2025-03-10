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
 * 订单操作历史记录
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("oms_order_operate_history")
@ApiModel(value = "OrderOperateHistory对象", description = "订单操作历史记录")
public class OrderOperateHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    @TableField("order_id")
    @ApiModelProperty("订单id")
    private Long orderId;

    /**
     * 操作人[用户；系统；后台管理员]
     */
    @TableField("operate_man")
    @ApiModelProperty("操作人[用户；系统；后台管理员]")
    private String operateMan;

    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
     */
    @TableField("order_status")
    @ApiModelProperty("订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】")
    private Byte orderStatus;

    /**
     * 备注
     */
    @TableField("note")
    @ApiModelProperty("备注")
    private String note;
}
