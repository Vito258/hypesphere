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
 * 属性分组
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_attr_group")
@ApiModel(value = "AttrGroup对象", description = "属性分组")
public class AttrGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    @ApiModelProperty("分组id")
    @TableId(value = "attr_group_id", type = IdType.AUTO)
    private Long attrGroupId;

    /**
     * 组名
     */
    @ApiModelProperty("组名")
    @TableField("attr_group_name")
    private String attrGroupName;

    /**
     * 排序
     */
    @TableField("sort")
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @TableField("descript")
    private String descript;

    /**
     * 组图标
     */
    @TableField("icon")
    @ApiModelProperty("组图标")
    private String icon;

    /**
     * 所属分类id
     */
    @TableField("catelog_id")
    @ApiModelProperty("所属分类id")
    private Long catelogId;

    /**
     * 所属分类完整路径
     */
    @TableField(exist = false)
    private Long[] catelogPath;
}
