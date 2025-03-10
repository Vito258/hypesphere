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
 * 商品库存
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("wms_ware_sku")
@ApiModel(value = "WareSku对象", description = "商品库存")
public class WareSku implements Serializable {

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
     * 仓库id
     */
    @TableField("ware_id")
    @ApiModelProperty("仓库id")
    private Long wareId;

    /**
     * 库存数
     */
    @TableField("stock")
    @ApiModelProperty("库存数")
    private Integer stock;

    /**
     * sku_name
     */
    @TableField("sku_name")
    @ApiModelProperty("sku_name")
    private String skuName;

    /**
     * 锁定库存
     */
    @ApiModelProperty("锁定库存")
    @TableField("stock_locked")
    private Integer stockLocked;
}
