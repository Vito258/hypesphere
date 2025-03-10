package com.elon.hypesphere.member.entity;

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
 * 会员等级
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("ums_member_level")
@ApiModel(value = "MemberLevel对象", description = "会员等级")
public class MemberLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 等级名称
     */
    @TableField("name")
    @ApiModelProperty("等级名称")
    private String name;

    /**
     * 等级需要的成长值
     */
    @TableField("growth_point")
    @ApiModelProperty("等级需要的成长值")
    private Integer growthPoint;

    /**
     * 是否为默认等级[0->不是；1->是]
     */
    @TableField("default_status")
    @ApiModelProperty("是否为默认等级[0->不是；1->是]")
    private Byte defaultStatus;

    /**
     * 免运费标准
     */
    @ApiModelProperty("免运费标准")
    @TableField("free_freight_point")
    private BigDecimal freeFreightPoint;

    /**
     * 每次评价获取的成长值
     */
    @ApiModelProperty("每次评价获取的成长值")
    @TableField("comment_growth_point")
    private Integer commentGrowthPoint;

    /**
     * 是否有免邮特权
     */
    @ApiModelProperty("是否有免邮特权")
    @TableField("priviledge_free_freight")
    private Byte priviledgeFreeFreight;

    /**
     * 是否有会员价格特权
     */
    @ApiModelProperty("是否有会员价格特权")
    @TableField("priviledge_member_price")
    private Byte priviledgeMemberPrice;

    /**
     * 是否有生日特权
     */
    @ApiModelProperty("是否有生日特权")
    @TableField("priviledge_birthday")
    private Byte priviledgeBirthday;

    /**
     * 备注
     */
    @TableField("note")
    @ApiModelProperty("备注")
    private String note;
}
