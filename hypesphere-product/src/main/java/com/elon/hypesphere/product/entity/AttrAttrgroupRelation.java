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
 * 属性&属性分组关联
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_attr_attrgroup_relation")
@ApiModel(value = "AttrAttrgroupRelation对象", description = "属性&属性分组关联")
public class AttrAttrgroupRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 属性id
     */
    @TableField("attr_id")
    @ApiModelProperty("属性id")
    private Long attrId;

    /**
     * 属性分组id
     */
    @ApiModelProperty("属性分组id")
    @TableField("attr_group_id")
    private Long attrGroupId;

    /**
     * 属性组内排序
     */
    @TableField("attr_sort")
    @ApiModelProperty("属性组内排序")
    private Integer attrSort;
}
