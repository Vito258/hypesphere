package com.elon.hypesphere.product.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class AttrRespVo extends AttrVo{
    /**
     * 所属分类名称
     */
    @TableField(exist = false)
    private String catelogName;

    /**
     * 所属分组名称
     */
    @TableField(exist = false)
    private String groupName;

    /**
     * 分类完整路径
     */
    private Long[] catelogPath;
}
