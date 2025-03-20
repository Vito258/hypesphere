package com.elon.hypesphere.product.mapper;
import com.elon.hypesphere.product.entity.Attr;
import com.elon.hypesphere.product.entity.AttrAttrgroupRelation;
import com.elon.hypesphere.product.vo.AttrGroupRelationVo;
import com.elon.hypesphere.product.vo.AttrRespVo;
import com.elon.hypesphere.product.vo.AttrVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper // 使用 Spring 组件模型
public interface AttrConvertMapper {
    AttrConvertMapper INSTANCE = Mappers.getMapper(AttrConvertMapper.class);

    // @Mapping 按需配置字段映射
    Attr attrVoToAttr(AttrVo attrVo);

    // @Mapping 按需配置字段映射
    AttrRespVo attrToAttrResponseVo(Attr attr);

    // @Mapping 按需配置字段映射
    Attr attrRespVoToAttr(AttrRespVo attrRespVo);

    AttrAttrgroupRelation attrGroupRelationVoToAttrAttrgroupRelation(AttrGroupRelationVo attrGroupRelationVo);
}
