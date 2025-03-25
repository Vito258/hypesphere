package com.elon.hypesphere.product.mapper;

import com.elon.hypesphere.common.to.SpuBoundsTo;
import com.elon.hypesphere.product.entity.SpuInfo;
import com.elon.hypesphere.product.vo.Bounds;
import com.elon.hypesphere.product.vo.SpuSaveVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpuConvertMapper {
    SpuConvertMapper Instance = Mappers.getMapper(SpuConvertMapper.class);

    SpuInfo spuSaveVoToSpuInfo(SpuSaveVo spuSaveVo);

    SpuBoundsTo boundsToSpuBoundsTo(Bounds bounds);
}
