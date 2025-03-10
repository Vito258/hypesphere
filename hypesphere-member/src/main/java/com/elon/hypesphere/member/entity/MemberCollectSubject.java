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
 * 会员收藏的专题活动
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("ums_member_collect_subject")
@ApiModel(value = "MemberCollectSubject对象", description = "会员收藏的专题活动")
public class MemberCollectSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * subject_id
     */
    @TableField("subject_id")
    @ApiModelProperty("subject_id")
    private Long subjectId;

    /**
     * subject_name
     */
    @TableField("subject_name")
    @ApiModelProperty("subject_name")
    private String subjectName;

    /**
     * subject_img
     */
    @TableField("subject_img")
    @ApiModelProperty("subject_img")
    private String subjectImg;

    /**
     * 活动url
     */
    @ApiModelProperty("活动url")
    @TableField("subject_urll")
    private String subjectUrll;
}
