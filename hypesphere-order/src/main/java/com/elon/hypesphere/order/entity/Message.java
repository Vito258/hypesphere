package com.elon.hypesphere.order.entity;

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
@TableName("mq_message")
@ApiModel(value = "Message对象", description = "")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("message_id")
    private String messageId;

    @TableField("content")
    private String content;

    @TableField("to_exchane")
    private String toExchane;

    @TableField("routing_key")
    private String routingKey;

    @TableField("class_type")
    private String classType;

    /**
     * 0-新建 1-已发送 2-错误抵达 3-已抵达
     */
    @TableField("message_status")
    @ApiModelProperty("0-新建 1-已发送 2-错误抵达 3-已抵达")
    private Integer messageStatus;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
