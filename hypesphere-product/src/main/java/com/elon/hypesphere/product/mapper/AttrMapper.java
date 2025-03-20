package com.elon.hypesphere.product.mapper;

import com.elon.hypesphere.product.entity.Attr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elon.hypesphere.product.vo.AttrRespVo;
import com.elon.hypesphere.product.vo.AttrVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


/**
 * <p>
 * 商品属性 Mapper 接口
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface AttrMapper extends BaseMapper<Attr> {
}

