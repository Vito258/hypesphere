package com.elon.hypesphere.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.elon.hypesphere.common.validator.ListValue;
import com.elon.hypesphere.common.validator.group.AddGroup;
import com.elon.hypesphere.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

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
    @Null(message = "新增不能指定ID",groups = {AddGroup.class})
    @NotNull(message = "修改必须指定ID", groups = {UpdateGroup.class})
    private Long brandId;

    /**
     * 品牌名
     */
    @TableField("name")
    @ApiModelProperty("品牌名")
    @NotBlank(message = "品牌名不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * 品牌logo地址
     */
    @TableField("logo")
    @ApiModelProperty("品牌logo地址")
    @NotNull(message = "logo地址不能为空",groups = {AddGroup.class})
    @URL(message = "logo地址必须是一个合法的url" ,groups = {AddGroup.class, UpdateGroup.class})
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
    @ListValue(vals = {0,1},message = "显示状态必须是0或1",groups = {AddGroup.class, UpdateGroup.class})
    private Byte showStatus;

    /**
     * 检索首字母
     */
    @ApiModelProperty("检索首字母")
    @TableField("first_letter")
    @NotNull(message = "首字母不能为空",groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$",message = "首字母必须在a~z，或者A~Z之间",groups = {AddGroup.class, UpdateGroup.class})
    private String firstLetter;

    /**
     * 排序
     */
    @TableField("sort")
    @ApiModelProperty("排序")
    @NotNull(message = "排序不能为空",groups = {AddGroup.class})
    @Min(value = 0,message = "排序必须大于等于0",groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;
}
