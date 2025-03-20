package com.elon.hypesphere.product.service;

import com.elon.hypesphere.product.entity.AttrAttrgroupRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elon.hypesphere.product.vo.AttrGroupRelationVo;

import java.util.List;

/**
 * <p>
 * 属性&属性分组关联 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IAttrAttrgroupRelationService extends IService<AttrAttrgroupRelation> {

    // 保存关联关系
    void saveRelationByVos(List<AttrGroupRelationVo> vos);
}
