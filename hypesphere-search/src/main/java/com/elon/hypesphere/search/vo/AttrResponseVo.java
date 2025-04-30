package com.elon.hypesphere.search.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AttrResponseVo{

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

    /**
     * 属性分组ID
     */
    @TableField(exist = false)
    private Long attrGroupId;

    /**
     * 所属分类名称
     */
    @TableField(exist = false)
    private String catelogName;

    /**
     * 所属分组名称
     */
    @TableField(exist = false)
    private String groupName;

    /**
     * 分类完整路径
     */
    private Long[] catelogPath;
}
