package com.elon.hypesphere.product.entity;

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
 * 商品评价回复关系
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("pms_comment_replay")
@ApiModel(value = "CommentReplay对象", description = "商品评价回复关系")
public class CommentReplay implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论id
     */
    @ApiModelProperty("评论id")
    @TableField("comment_id")
    private Long commentId;

    /**
     * 回复id
     */
    @TableField("reply_id")
    @ApiModelProperty("回复id")
    private Long replyId;
}
