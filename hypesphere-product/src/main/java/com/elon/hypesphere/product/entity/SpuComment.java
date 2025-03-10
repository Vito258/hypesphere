package com.elon.hypesphere.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 商品评价
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_spu_comment")
@ApiModel(value = "SpuComment对象", description = "商品评价")
public class SpuComment implements Serializable {

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
     * spu_id
     */
    @TableField("spu_id")
    @ApiModelProperty("spu_id")
    private Long spuId;

    /**
     * 商品名字
     */
    @TableField("spu_name")
    @ApiModelProperty("商品名字")
    private String spuName;

    /**
     * 会员昵称
     */
    @ApiModelProperty("会员昵称")
    @TableField("member_nick_name")
    private String memberNickName;

    /**
     * 星级
     */
    @TableField("star")
    @ApiModelProperty("星级")
    private Boolean star;

    /**
     * 会员ip
     */
    @TableField("member_ip")
    @ApiModelProperty("会员ip")
    private String memberIp;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 显示状态[0-不显示，1-显示]
     */
    @TableField("show_status")
    @ApiModelProperty("显示状态[0-不显示，1-显示]")
    private Boolean showStatus;

    /**
     * 购买时属性组合
     */
    @ApiModelProperty("购买时属性组合")
    @TableField("spu_attributes")
    private String spuAttributes;

    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    @TableField("likes_count")
    private Integer likesCount;

    /**
     * 回复数
     */
    @ApiModelProperty("回复数")
    @TableField("reply_count")
    private Integer replyCount;

    /**
     * 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
     */
    @TableField("resources")
    @ApiModelProperty("评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
    private String resources;

    /**
     * 内容
     */
    @TableField("content")
    @ApiModelProperty("内容")
    private String content;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    @TableField("member_icon")
    private String memberIcon;

    /**
     * 评论类型[0 - 对商品的直接评论，1 - 对评论的回复]
     */
    @TableField("comment_type")
    @ApiModelProperty("评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
    private Byte commentType;
}
