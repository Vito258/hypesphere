package com.elon.hypesphere.product.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.product.entity.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elon.hypesphere.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * <p>
 * spu信息 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface ISpuInfoService extends IService<SpuInfo> {

    // 保存spu信息
    void saveSpuInfo(SpuSaveVo spuSaveVo);

    // 分页查询spu信息
    PageUtils queryPageByCondition(Map<String, Object> params);
}
