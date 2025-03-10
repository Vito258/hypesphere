package com.elon.hypesphere.member.entity;

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
 * 会员收货地址
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("ums_member_receive_address")
@ApiModel(value = "MemberReceiveAddress对象", description = "会员收货地址")
public class MemberReceiveAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * member_id
     */
    @TableField("member_id")
    @ApiModelProperty("member_id")
    private Long memberId;

    /**
     * 收货人姓名
     */
    @TableField("name")
    @ApiModelProperty("收货人姓名")
    private String name;

    /**
     * 电话
     */
    @TableField("phone")
    @ApiModelProperty("电话")
    private String phone;

    /**
     * 邮政编码
     */
    @TableField("post_code")
    @ApiModelProperty("邮政编码")
    private String postCode;

    /**
     * 省份/直辖市
     */
    @TableField("province")
    @ApiModelProperty("省份/直辖市")
    private String province;

    /**
     * 城市
     */
    @TableField("city")
    @ApiModelProperty("城市")
    private String city;

    /**
     * 区
     */
    @TableField("region")
    @ApiModelProperty("区")
    private String region;

    /**
     * 详细地址(街道)
     */
    @ApiModelProperty("详细地址(街道)")
    @TableField("detail_address")
    private String detailAddress;

    /**
     * 省市区代码
     */
    @TableField("areacode")
    @ApiModelProperty("省市区代码")
    private String areacode;

    /**
     * 是否默认
     */
    @ApiModelProperty("是否默认")
    @TableField("default_status")
    private Boolean defaultStatus;
}
