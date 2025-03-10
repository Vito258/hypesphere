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
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("sms_home_subject")
@ApiModel(value = "HomeSubject对象", description = "首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】")
public class HomeSubject implements Serializable {

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
     * 专题标题
     */
    @TableField("title")
    @ApiModelProperty("专题标题")
    private String title;

    /**
     * 专题副标题
     */
    @TableField("sub_title")
    @ApiModelProperty("专题副标题")
    private String subTitle;

    /**
     * 显示状态
     */
    @TableField("status")
    @ApiModelProperty("显示状态")
    private Boolean status;

    /**
     * 详情连接
     */
    @TableField("url")
    @ApiModelProperty("详情连接")
    private String url;

    /**
     * 排序
     */
    @TableField("sort")
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 专题图片地址
     */
    @TableField("img")
    @ApiModelProperty("专题图片地址")
    private String img;
}
