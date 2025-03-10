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
 * 品牌
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_brand")
@ApiModel(value = "Brand对象", description = "品牌")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @ApiModelProperty("品牌id")
    @TableId(value = "brand_id", type = IdType.AUTO)
    private Long brandId;

    /**
     * 品牌名
     */
    @TableField("name")
    @ApiModelProperty("品牌名")
    private String name;

    /**
     * 品牌logo地址
     */
    @TableField("logo")
    @ApiModelProperty("品牌logo地址")
    private String logo;

    /**
     * 介绍
     */
    @ApiModelProperty("介绍")
    @TableField("descript")
    private String descript;

    /**
     * 显示状态[0-不显示；1-显示]
     */
    @TableField("show_status")
    @ApiModelProperty("显示状态[0-不显示；1-显示]")
    private Byte showStatus;

    /**
     * 检索首字母
     */
    @ApiModelProperty("检索首字母")
    @TableField("first_letter")
    private String firstLetter;

    /**
     * 排序
     */
    @TableField("sort")
    @ApiModelProperty("排序")
    private Integer sort;
}
