package com.elon.hypesphere.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * sku信息
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_sku_info")
@ApiModel(value = "SkuInfo对象", description = "sku信息")
public class SkuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * skuId
     */
    @ApiModelProperty("skuId")
    @TableId(value = "sku_id", type = IdType.AUTO)
    private Long skuId;

    /**
     * spuId
     */
    @TableField("spu_id")
    @ApiModelProperty("spuId")
    private Long spuId;

    /**
     * sku名称
     */
    @TableField("sku_name")
    @ApiModelProperty("sku名称")
    private String skuName;

    /**
     * sku介绍描述
     */
    @TableField("sku_desc")
    @ApiModelProperty("sku介绍描述")
    private String skuDesc;

    /**
     * 所属分类id
     */
    @TableField("catalog_id")
    @ApiModelProperty("所属分类id")
    private Long catalogId;

    /**
     * 品牌id
     */
    @TableField("brand_id")
    @ApiModelProperty("品牌id")
    private Long brandId;

    /**
     * 默认图片
     */
    @ApiModelProperty("默认图片")
    @TableField("sku_default_img")
    private String skuDefaultImg;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    @TableField("sku_title")
    private String skuTitle;

    /**
     * 副标题
     */
    @ApiModelProperty("副标题")
    @TableField("sku_subtitle")
    private String skuSubtitle;

    /**
     * 价格
     */
    @TableField("price")
    @ApiModelProperty("价格")
    private BigDecimal price;

    /**
     * 销量
     */
    @ApiModelProperty("销量")
    @TableField("sale_count")
    private Long saleCount;
}
