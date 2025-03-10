package com.elon.hypesphere.coupon.entity;

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
 * 专题商品
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_home_subject_spu")
@ApiModel(value = "HomeSubjectSpu对象", description = "专题商品")
public class HomeSubjectSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 专题名字
     */
    @TableField("name")
    @ApiModelProperty("专题名字")
    private String name;

    /**
     * 专题id
     */
    @ApiModelProperty("专题id")
    @TableField("subject_id")
    private Long subjectId;

    /**
     * spu_id
     */
    @TableField("spu_id")
    @ApiModelProperty("spu_id")
    private Long spuId;

    /**
     * 排序
     */
    @TableField("sort")
    @ApiModelProperty("排序")
    private Integer sort;
}
