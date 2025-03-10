package com.elon.hypesphere.product.entity;

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
 * sku销售属性&值
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_sku_sale_attr_value")
@ApiModel(value = "SkuSaleAttrValue对象", description = "sku销售属性&值")
public class SkuSaleAttrValue implements Serializable {

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
     * attr_id
     */
    @TableField("attr_id")
    @ApiModelProperty("attr_id")
    private Long attrId;

    /**
     * 销售属性名
     */
    @TableField("attr_name")
    @ApiModelProperty("销售属性名")
    private String attrName;

    /**
     * 销售属性值
     */
    @TableField("attr_value")
    @ApiModelProperty("销售属性值")
    private String attrValue;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    @TableField("attr_sort")
    private Integer attrSort;
}
