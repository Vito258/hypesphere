package com.elon.hypesphere.product.service;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.product.entity.Attr;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elon.hypesphere.product.vo.AttrGroupRelationVo;
import com.elon.hypesphere.product.vo.AttrRespVo;
import com.elon.hypesphere.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
public interface IAttrService extends IService<Attr> {

    // 分页查询
    PageUtils queryPage(Map<String, Object> params);

    // 列出当前属性分组关联的所有属性
    List<Attr> getRelationAttr(Long attrgroupId);

    // 分页查询当前分类关联的属性
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    // 保存属性
    void saveAttr(AttrVo attr);

    // 查询属性详情
    AttrRespVo getAttrInfo(Long attrId);

    // 修改属性
    void updateAttr(AttrVo attr);

    // 删除关联信息
    void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos);

    // 获取当前分组所有未关联的属性
    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

    // 查询可以检索的属性
    List<Long> selectSearchAttrs(List<Long> attrIds);
}
