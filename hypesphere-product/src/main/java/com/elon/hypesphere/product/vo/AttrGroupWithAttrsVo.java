package com.elon.hypesphere.product.vo;

import com.elon.hypesphere.product.entity.Attr;
import com.elon.hypesphere.product.entity.AttrGroup;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupWithAttrsVo extends AttrGroup {
    // 分组下的所有属性
    private List<Attr> attrs ;
}
