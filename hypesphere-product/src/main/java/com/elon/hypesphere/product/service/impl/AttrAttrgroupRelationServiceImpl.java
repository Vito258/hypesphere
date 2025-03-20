package com.elon.hypesphere.product.service.impl;

import com.elon.hypesphere.product.entity.AttrAttrgroupRelation;
import com.elon.hypesphere.product.mapper.AttrAttrgroupRelationMapper;
import com.elon.hypesphere.product.mapper.AttrConvertMapper;
import com.elon.hypesphere.product.service.IAttrAttrgroupRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elon.hypesphere.product.vo.AttrGroupRelationVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 属性&属性分组关联 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationMapper, AttrAttrgroupRelation> implements IAttrAttrgroupRelationService {

    /**
     * 根据Vo保存关联关系
     * @param vos
     */
    @Override
    public void saveRelationByVos(List<AttrGroupRelationVo> vos) {
        List<AttrAttrgroupRelation> relations = vos.stream().map(vo -> {
            return AttrConvertMapper.INSTANCE.attrGroupRelationVoToAttrAttrgroupRelation(vo);
        }).collect(Collectors.toList());
        saveBatch(relations);
    }
}
