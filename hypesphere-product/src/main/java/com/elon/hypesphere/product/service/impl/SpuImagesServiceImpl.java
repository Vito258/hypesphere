package com.elon.hypesphere.product.service.impl;

import com.elon.hypesphere.product.entity.SpuImages;
import com.elon.hypesphere.product.mapper.SpuImagesMapper;
import com.elon.hypesphere.product.service.ISpuImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * spu图片 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesMapper, SpuImages> implements ISpuImagesService {

    /**
     * 保存Spu图片集
     *
     * @param id
     * @param images
     */
    @Override
    public void saveImages(Long id, List<String> images) {
        if (images != null && images.size() > 0) {
            // 批量保存
            List<SpuImages> spuImages = images.stream().map(img -> {
                SpuImages spuImage = new SpuImages();
                spuImage.setSpuId(id);
                spuImage.setImgUrl(img);
                return spuImage;
            }).collect(Collectors.toList());

            this.saveBatch(spuImages);
        }
    }
}
