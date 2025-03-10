package com.elon.hypesphere.member.entity;

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
 * 会员收藏的商品
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("ums_member_collect_spu")
@ApiModel(value = "MemberCollectSpu对象", description = "会员收藏的商品")
public class MemberCollectSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    @ApiModelProperty("id")
    private Long id;

    /**
     * 会员id
     */
    @TableField("member_id")
    @ApiModelProperty("会员id")
    private Long memberId;

    /**
     * spu_id
     */
    @TableField("spu_id")
    @ApiModelProperty("spu_id")
    private Long spuId;

    /**
     * spu_name
     */
    @TableField("spu_name")
    @ApiModelProperty("spu_name")
    private String spuName;

    /**
     * spu_img
     */
    @TableField("spu_img")
    @ApiModelProperty("spu_img")
    private String spuImg;

    /**
     * create_time
     */
    @TableField("create_time")
    @ApiModelProperty("create_time")
    private LocalDateTime createTime;
}
