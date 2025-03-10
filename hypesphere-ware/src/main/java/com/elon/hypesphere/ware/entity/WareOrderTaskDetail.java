package com.elon.hypesphere.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 库存工作单
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("wms_ware_order_task_detail")
@ApiModel(value = "WareOrderTaskDetail对象", description = "库存工作单")
public class WareOrderTaskDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * sku_id
     */
    @TableField("sku_id")
    @ApiModelProperty("sku_id")
    private Long skuId;

    /**
     * sku_name
     */
    @TableField("sku_name")
    @ApiModelProperty("sku_name")
    private String skuName;

    /**
     * 购买个数
     */
    @TableField("sku_num")
    @ApiModelProperty("购买个数")
    private Integer skuNum;

    /**
     * 工作单id
     */
    @TableField("task_id")
    @ApiModelProperty("工作单id")
    private Long taskId;

    /**
     * 仓库id
     */
    @TableField("ware_id")
    @ApiModelProperty("仓库id")
    private Long wareId;

    /**
     * 1-已锁定  2-已解锁  3-扣减
     */
    @TableField("lock_status")
    @ApiModelProperty("1-已锁定  2-已解锁  3-扣减")
    private Integer lockStatus;
}
