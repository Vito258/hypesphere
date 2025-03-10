package com.elon.hypesphere.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 会员
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("ums_member")
@ApiModel(value = "Member对象", description = "会员")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员等级id
     */
    @TableField("level_id")
    @ApiModelProperty("会员等级id")
    private Long levelId;

    /**
     * 用户名
     */
    @TableField("username")
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    @TableField("nickname")
    private String nickname;

    /**
     * 手机号码
     */
    @TableField("mobile")
    @ApiModelProperty("手机号码")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField("email")
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 头像
     */
    @TableField("header")
    @ApiModelProperty("头像")
    private String header;

    /**
     * 性别
     */
    @TableField("gender")
    @ApiModelProperty("性别")
    private Byte gender;

    /**
     * 生日
     */
    @TableField("birth")
    @ApiModelProperty("生日")
    private LocalDate birth;

    /**
     * 所在城市
     */
    @TableField("city")
    @ApiModelProperty("所在城市")
    private String city;

    /**
     * 职业
     */
    @TableField("job")
    @ApiModelProperty("职业")
    private String job;

    /**
     * 个性签名
     */
    @TableField("sign")
    @ApiModelProperty("个性签名")
    private String sign;

    /**
     * 用户来源
     */
    @ApiModelProperty("用户来源")
    @TableField("source_type")
    private Byte sourceType;

    /**
     * 积分
     */
    @ApiModelProperty("积分")
    @TableField("integration")
    private Integer integration;

    /**
     * 成长值
     */
    @TableField("growth")
    @ApiModelProperty("成长值")
    private Integer growth;

    /**
     * 启用状态
     */
    @TableField("status")
    @ApiModelProperty("启用状态")
    private Byte status;

    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 微博社交账户uid（应该建立关联表）
     */
    @TableField("weibo_uid")
    @ApiModelProperty("微博社交账户uid（应该建立关联表）")
    private String weiboUid;

    /**
     * 访问令牌
     */
    @ApiModelProperty("访问令牌")
    @TableField("access_token")
    private String accessToken;

    /**
     * 访问令牌的过期时间
     */
    @TableField("expires_in")
    @ApiModelProperty("访问令牌的过期时间")
    private String expiresIn;
}
