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
 * 商品属性
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_attr")
@ApiModel(value = "Attr对象", description = "商品属性")
public class Attr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    @ApiModelProperty("属性id")
    @TableId(value = "attr_id", type = IdType.AUTO)
    private Long attrId;

    /**
     * 属性名
     */
    @ApiModelProperty("属性名")
    @TableField("attr_name")
    private String attrName;

    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @TableField("search_type")
    @ApiModelProperty("是否需要检索[0-不需要，1-需要]")
    private Byte searchType;

    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    @TableField("value_type")
    @ApiModelProperty("值类型[0-为单个值，1-可以选择多个值]")
    private Byte valueType;

    /**
     * 属性图标
     */
    @TableField("icon")
    @ApiModelProperty("属性图标")
    private String icon;

    /**
     * 可选值列表[用逗号分隔]
     */
    @TableField("value_select")
    @ApiModelProperty("可选值列表[用逗号分隔]")
    private String valueSelect;

    /**
     * 属性类型[0-销售属性，1-基本属性
     */
    @TableField("attr_type")
    @ApiModelProperty("属性类型[0-销售属性，1-基本属性")
    private Byte attrType;

    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @TableField("enable")
    @ApiModelProperty("启用状态[0 - 禁用，1 - 启用]")
    private Long enable;

    /**
     * 所属分类
     */
    @ApiModelProperty("所属分类")
    @TableField("catelog_id")
    private Long catelogId;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    @TableField("show_desc")
    @ApiModelProperty("快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    private Byte showDesc;
}
