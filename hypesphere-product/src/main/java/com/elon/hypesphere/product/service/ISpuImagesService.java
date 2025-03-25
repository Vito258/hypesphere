package com.elon.hypesphere.product.service;

import com.elon.hypesphere.product.entity.SpuImages;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * spu图片 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface ISpuImagesService extends IService<SpuImages> {

    // 保存Spu 图片集
    void saveImages(Long id, List<String> images);
}
