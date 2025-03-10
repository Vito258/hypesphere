package com.elon.hypesphere.product.entity;

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
 * spu信息介绍
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_spu_info_desc")
@ApiModel(value = "SpuInfoDesc对象", description = "spu信息介绍")
public class SpuInfoDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId("spu_id")
    @ApiModelProperty("商品id")
    private Long spuId;

    /**
     * 商品介绍
     */
    @TableField("decript")
    @ApiModelProperty("商品介绍")
    private String decript;
}
