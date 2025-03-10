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
 * spu属性值
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_product_attr_value")
@ApiModel(value = "ProductAttrValue对象", description = "spu属性值")
public class ProductAttrValue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    @TableField("spu_id")
    @ApiModelProperty("商品id")
    private Long spuId;

    /**
     * 属性id
     */
    @TableField("attr_id")
    @ApiModelProperty("属性id")
    private Long attrId;

    /**
     * 属性名
     */
    @ApiModelProperty("属性名")
    @TableField("attr_name")
    private String attrName;

    /**
     * 属性值
     */
    @ApiModelProperty("属性值")
    @TableField("attr_value")
    private String attrValue;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    @TableField("attr_sort")
    private Integer attrSort;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】
     */
    @TableField("quick_show")
    @ApiModelProperty("快速展示【是否展示在介绍上；0-否 1-是】")
    private Byte quickShow;
}
