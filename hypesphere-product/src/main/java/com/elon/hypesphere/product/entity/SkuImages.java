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
 * sku图片
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_sku_images")
@ApiModel(value = "SkuImages对象", description = "sku图片")
public class SkuImages implements Serializable {

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
     * 图片地址
     */
    @TableField("img_url")
    @ApiModelProperty("图片地址")
    private String imgUrl;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    @TableField("img_sort")
    private Integer imgSort;

    /**
     * 默认图[0 - 不是默认图，1 - 是默认图]
     */
    @TableField("default_img")
    @ApiModelProperty("默认图[0 - 不是默认图，1 - 是默认图]")
    private Integer defaultImg;
}
