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
 * 品牌分类关联
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_category_brand_relation")
@ApiModel(value = "CategoryBrandRelation对象", description = "品牌分类关联")
public class CategoryBrandRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 品牌id
     */
    @TableField("brand_id")
    @ApiModelProperty("品牌id")
    private Long brandId;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    @TableField("catelog_id")
    private Long catelogId;

    @TableField("brand_name")
    private String brandName;

    @TableField("catelog_name")
    private String catelogName;
}
