package com.elon.hypesphere.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elon.hypesphere.common.to.SkuReductionTo;
import com.elon.hypesphere.common.to.SpuBoundsTo;
import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.Query;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.product.entity.*;
import com.elon.hypesphere.product.feign.CouponFeignService;
import com.elon.hypesphere.product.mapper.SkuConvertMapper;
import com.elon.hypesphere.product.mapper.SpuConvertMapper;
import com.elon.hypesphere.product.mapper.SpuInfoDescMapper;
import com.elon.hypesphere.product.mapper.SpuInfoMapper;
import com.elon.hypesphere.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elon.hypesphere.product.vo.*;
import com.elon.hypesphere.product.vo.Attr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * spu信息 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements ISpuInfoService {

    @Autowired
    private ISpuInfoDescService spuInfoDescService;

    @Autowired
    private ISpuImagesService spuImagesService;

    @Autowired
    private IAttrService attrService;

    @Autowired
    private IProductAttrValueService productAttrValueService;

    @Autowired
    private ISkuInfoService skuInfoService;

    @Autowired
    private ISkuImagesService skuImagesService;

    @Autowired
    private ISkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private CouponFeignService couponFeignService;

    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo spuSaveVo) {
        // 1、保存基本信息 pms_spu_info
        SpuInfo spuInfo = SpuConvertMapper.Instance.spuSaveVoToSpuInfo(spuSaveVo);
        save(spuInfo);

        // 2、保存Spu描述信息  pms_spu_info_desc
        List<String> decript = spuSaveVo.getDecript();
        SpuInfoDesc spuInfoDesc = new SpuInfoDesc();
        spuInfoDesc.setSpuId(spuInfo.getId());
        spuInfoDesc.setDecript(String.join(",", decript));
        spuInfoDescService.save(spuInfoDesc);

        // 3、保存Spu图片信息 pms_spu_images
        spuImagesService.saveImages(spuInfo.getId(), spuSaveVo.getImages());

        // 4、保存Spu规格信息 pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        List<ProductAttrValue> ProductAttrValues = baseAttrs.stream().map(item -> {
            ProductAttrValue attrValue = new ProductAttrValue();
            attrValue.setAttrId(item.getAttrId());
            attrValue.setAttrName(attrService.getById(item.getAttrId()).getAttrName());
            attrValue.setAttrValue(item.getAttrValues());
            attrValue.setQuickShow((byte) item.getShowDesc());
            attrValue.setSpuId(spuInfo.getId());
            return attrValue;
        }).collect(Collectors.toList());
        productAttrValueService.saveBatch(ProductAttrValues);

        // 5、Spu 积分信息 sms_spu_bounds
        Bounds bounds = spuSaveVo.getBounds();
        SpuBoundsTo spuBoundsTo = SpuConvertMapper.Instance.boundsToSpuBoundsTo(bounds);
        spuBoundsTo.setSpuId(spuInfo.getId());
        R r = couponFeignService.saveSpuBounds(spuBoundsTo);

        // 5.1)远程调用成功，返回成功，否则，抛出异常，事务回滚
        if (r.getCode() != 0) {
            log.error("远程保存Spu 积分信息失败");
        }

        // 6、保存Spu对应的Sku参数信息
        // 6.1)Sku 基本信息 pms_sku_info
        List<Skus> skus = spuSaveVo.getSkus();
        if (skus != null && skus.size() > 0) {
            skus.forEach(item -> {
                // 找到默认图片
                String defaultImg = "";
                for (Images img : item.getImages()) {
                    if (img.getDefaultImg() == 1) {
                        defaultImg = img.getImgUrl();
                    }
                }

                SkuInfo skuInfo = SkuConvertMapper.Instance.skusInfoVoToSkuInfo(item);
                skuInfo.setSpuId(spuInfo.getId());
                skuInfo.setBrandId(spuInfo.getBrandId());
                skuInfo.setCatalogId(spuInfo.getCatalogId());
                skuInfo.setSaleCount(0L);
                skuInfo.setSkuDefaultImg(defaultImg);
                skuInfoService.save(skuInfo);

                // 保存后可以拿到自增主键
                Long skuId = skuInfo.getSkuId();

                // 获取到图片信息并过滤掉为null的值
                List<SkuImages> images = item.getImages().stream().map(img -> {
                    SkuImages skuImages = new SkuImages();
                    skuImages.setSkuId(skuId);
                    skuImages.setImgUrl(img.getImgUrl());
                    skuImages.setDefaultImg(img.getDefaultImg());
                    return skuImages;
                }).filter(img -> img.getImgUrl() != null).collect(Collectors.toList());

                // 6.2)Sku 图片信息 pms_sku_images
                skuImagesService.saveBatch(images);

                // 6.3)Sku 销售属性 pms_sku_sale_attr_value
                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValue> skuSaleAttrValues = attr.stream().map(itemAttr -> {
                    SkuSaleAttrValue skuSaleAttrValue = SkuConvertMapper.Instance.attrToSkuSaleAttrValue(itemAttr);
                    skuSaleAttrValue.setSkuId(skuId);
                    return skuSaleAttrValue;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValues);

                // 6.4)Sku 优惠信息 sms_sku_ladder sms_sku_full_reduction sms_member_price
                SkuReductionTo skuReductionTo = SkuConvertMapper.Instance.skusToSkuReductionTo(item);
                skuReductionTo.setSkuId(skuId);
                if (skuReductionTo.getFullCount() > 0 || skuReductionTo.getFullPrice().compareTo(BigDecimal.ZERO) == 1) {
                    R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
                    if (r1.getCode() != 0) {
                        log.error("远程保存Sku 优惠信息失败");
                    }
                }

            });
        }

        // 5.6)Sku 满减信息
        // 5.7)Sku 库存信息
    }

    /**
     * 分页查询列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and(w -> {
                w.eq("id", key).or().like("spu_name", key);
            });
        }

        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId)) {
            wrapper.eq("catalog_id", catelogId);
        }

        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId)) {
            wrapper.eq("brand_id", brandId);
        }

        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("publish_status", status);
        }

        IPage<SpuInfo> page = page(
                new Query<SpuInfo>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }
}
