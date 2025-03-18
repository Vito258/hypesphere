package com.elon.hypesphere.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 商品三级分类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_category")
@ApiModel(value = "Category对象", description = "商品三级分类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    @TableId(value = "cat_id", type = IdType.AUTO)
    private Long catId;

    /**
     * 分类名称
     */
    @TableField("name")
    @ApiModelProperty("分类名称")
    private String name;

    /**
     * 父分类id
     */
    @TableField("parent_cid")
    @ApiModelProperty("父分类id")
    private Long parentCid;

    /**
     * 层级
     */
    @ApiModelProperty("层级")
    @TableField("cat_level")
    private Integer catLevel;

    /**
     * 是否显示[0-不显示，1显示]
     */
    @TableField("show_status")
    @ApiModelProperty("是否显示[0-不显示，1显示]")
    @TableLogic
    private Byte showStatus;

    /**
     * 排序
     */
    @TableField("sort")
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 图标地址
     */
    @TableField("icon")
    @ApiModelProperty("图标地址")
    private String icon;

    /**
     * 计量单位
     */
    @ApiModelProperty("计量单位")
    @TableField("product_unit")
    private String productUnit;

    /**
     * 商品数量
     */
    @ApiModelProperty("商品数量")
    @TableField("product_count")
    private Integer productCount;

    /**
     * 子分类
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)   // 如果children为空，则不返回给前端
    private List<Category> children;
}
