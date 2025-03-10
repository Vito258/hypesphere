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
 * spu图片
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_spu_images")
@ApiModel(value = "SpuImages对象", description = "spu图片")
public class SpuImages implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * spu_id
     */
    @TableField("spu_id")
    @ApiModelProperty("spu_id")
    private Long spuId;

    /**
     * 图片名
     */
    @TableField("img_name")
    @ApiModelProperty("图片名")
    private String imgName;

    /**
     * 图片地址
     */
    @TableField("img_url")
    @ApiModelProperty("图片地址")
    private String imgUrl;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    @TableField("img_sort")
    private Integer imgSort;

    /**
     * 是否默认图
     */
    @ApiModelProperty("是否默认图")
    @TableField("default_img")
    private Byte defaultImg;
}
