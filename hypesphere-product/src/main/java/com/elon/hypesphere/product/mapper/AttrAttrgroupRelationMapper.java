package com.elon.hypesphere.product.mapper;

import com.elon.hypesphere.product.entity.AttrAttrgroupRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;


/**
 * <p>
 * 属性&属性分组关联 Mapper 接口
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface AttrAttrgroupRelationMapper extends BaseMapper<AttrAttrgroupRelation> {

    List<Long> selectIdsByAttrGroupIdAndAttrId(
            @Param("attrGroupId") Long attrGroupId,
            @Param("attrId") Long attrId
    );
}

