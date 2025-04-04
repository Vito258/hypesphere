package com.elon.hypesphere.product.mapper;

import com.elon.hypesphere.common.to.SkuReductionTo;
import com.elon.hypesphere.common.to.es.SkuEsModel;
import com.elon.hypesphere.product.entity.ProductAttrValue;
import com.elon.hypesphere.product.entity.SkuInfo;
import com.elon.hypesphere.product.entity.SkuSaleAttrValue;
import com.elon.hypesphere.product.vo.Attr;
import com.elon.hypesphere.product.vo.Skus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SkuConvertMapper {
    SkuConvertMapper Instance = Mappers.getMapper(SkuConvertMapper.class);

    SkuInfo skusInfoVoToSkuInfo(Skus skus);

    SkuSaleAttrValue attrToSkuSaleAttrValue(Attr attr);

    SkuReductionTo skusToSkuReductionTo(Skus skus);

    @Mappings({
            @Mapping(source = "price", target = "skuPrice"),
            @Mapping(source = "skuDefaultImg",target = "skuImg")
    })
    SkuEsModel skuInfoToSkuEsModel(SkuInfo skuInfo);

    SkuEsModel.Attrs productAttrValueToSkuEsModelAttrs(ProductAttrValue productAttrValue);
}
