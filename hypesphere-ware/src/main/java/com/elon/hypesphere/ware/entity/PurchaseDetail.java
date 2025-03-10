package com.elon.hypesphere.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 采购需求
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("wms_purchase_detail")
@ApiModel(value = "PurchaseDetail对象", description = "采购需求")
public class PurchaseDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 采购单id
     */
    @ApiModelProperty("采购单id")
    @TableField("purchase_id")
    private Long purchaseId;

    /**
     * 采购商品id
     */
    @TableField("sku_id")
    @ApiModelProperty("采购商品id")
    private Long skuId;

    /**
     * 采购数量
     */
    @TableField("sku_num")
    @ApiModelProperty("采购数量")
    private Integer skuNum;

    /**
     * 采购金额
     */
    @TableField("sku_price")
    @ApiModelProperty("采购金额")
    private BigDecimal skuPrice;

    /**
     * 仓库id
     */
    @TableField("ware_id")
    @ApiModelProperty("仓库id")
    private Long wareId;

    /**
     * 状态[0新建，1已分配，2正在采购，3已完成，4采购失败]
     */
    @TableField("status")
    @ApiModelProperty("状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
    private Integer status;
}
