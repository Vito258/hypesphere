package com.elon.hypesphere.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * spu信息
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_spu_info")
@ApiModel(value = "SpuInfo对象", description = "spu信息")
public class SpuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @TableField("spu_name")
    @ApiModelProperty("商品名称")
    private String spuName;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    @TableField("spu_description")
    private String spuDescription;

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

    @TableField("weight")
    private BigDecimal weight;

    /**
     * 上架状态[0 - 新建，1 - 上架，2 - 下架]
     */
    @TableField("publish_status")
    @ApiModelProperty("上架状态[0 - 新建，1 - 上架，2 - 下架]")
    private Byte publishStatus;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
