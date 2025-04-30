package com.elon.hypesphere.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elon.hypesphere.common.constant.ProductConstant;
import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.Query;
import com.elon.hypesphere.product.entity.Attr;
import com.elon.hypesphere.product.entity.AttrAttrgroupRelation;
import com.elon.hypesphere.product.entity.AttrGroup;
import com.elon.hypesphere.product.entity.Category;
import com.elon.hypesphere.product.mapper.*;
import com.elon.hypesphere.product.service.IAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elon.hypesphere.product.service.ICategoryService;
import com.elon.hypesphere.product.vo.AttrGroupRelationVo;
import com.elon.hypesphere.product.vo.AttrRespVo;
import com.elon.hypesphere.product.vo.AttrVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements IAttrService {

    @Autowired
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Autowired
    private AttrGroupMapper attrGroupMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ICategoryService categoryService;

    /**
     * 分页查询所有数据
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Attr> page = this.page(
                new Query<Attr>().getPage(params),
                new QueryWrapper<Attr>()
        );

        return new PageUtils(page);
    }

    /**
     * 列出当前属性分组关联的所有属性
     *
     * @param attrgroupId
     * @return
     */
    @Override
    public List<Attr> getRelationAttr(Long attrgroupId) {
        // 根据分组信息查询关联关系
        List<AttrAttrgroupRelation> relations = attrAttrgroupRelationMapper.selectList(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_group_id", attrgroupId));

        // 遍历查询关系，根据attrId 查出所有属性信息
        List<Long> attrIds = relations.stream().map(AttrAttrgroupRelation::getAttrId).collect(Collectors.toList());
        if (attrIds != null && attrIds.size() > 0) {
            List<Attr> attrList = this.listByIds(attrIds);
            return attrList;
        }
        return List.of();
    }

    /**
     * 分页查询属性分组关联的属性
     *
     * @param params
     * @param catelogId
     * @param type
     * @return
     */
    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type) {
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<Attr>().eq("attr_type", "base".equalsIgnoreCase(type) ? ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() : ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());

        if (catelogId != 0) {
            queryWrapper.eq("catelog_id", catelogId);
        }
        // 检索条件（模糊查询）
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            // 根据key多字段模糊查询
            // queryWrapper.eq("attr_id", key).or().like("attr_name", key);
            queryWrapper.and(wrapper -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }

        IPage<Attr> page = this.page(
                new Query<Attr>().getPage(params),
                queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);

        // 使用属性分组id查询属性
        List<Attr> records = page.getRecords();
        List<AttrRespVo> list = records.stream().map(attr -> {
            AttrRespVo attrRespVo = AttrConvertMapper.INSTANCE.attrToAttrResponseVo(attr);
            // 设置分类和分组的名字,只有为基本信息时才设置分组信息，销售属性不设置
            if ("base".equalsIgnoreCase(type)) {
                AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.selectOne(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attr.getAttrId()));
                if (attrAttrgroupRelation != null && attrAttrgroupRelation.getAttrGroupId() != null && attrAttrgroupRelation.getAttrId() != null) {
                    Long attrGroupId = attrAttrgroupRelation.getAttrGroupId();
                    AttrGroup attrGroup = attrGroupMapper.selectOne(new QueryWrapper<AttrGroup>().eq("attr_group_id", attrGroupId));

                    attrRespVo.setGroupName(attrGroup.getAttrGroupName());
                }
            }

            // 设置分类信息
            Category category = categoryMapper.selectOne(new QueryWrapper<Category>().eq("cat_id", attr.getCatelogId()));
            if (category != null)
                attrRespVo.setCatelogName(category.getName());

            return attrRespVo;
        }).collect(Collectors.toList());

        // 设置结果集
        pageUtils.setList(list);
        return pageUtils;
    }

    /**
     * 保存属性
     *
     * @param attrVo
     */
    @Transactional
    @Override
    public void saveAttr(AttrVo attrVo) {
        // 1. 保存基本数据
        Attr attrEntity = AttrConvertMapper.INSTANCE.attrVoToAttr(attrVo);
        this.save(attrEntity);
        // 2. 保存关联数据
        if(attrVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            AttrAttrgroupRelation relationEntity = new AttrAttrgroupRelation();
            relationEntity.setAttrId(attrEntity.getAttrId());
            relationEntity.setAttrGroupId(attrVo.getAttrGroupId());
            attrAttrgroupRelationMapper.insert(relationEntity);
        }
    }

    /**
     * 根据属性id查询属性详情
     *
     * @param attrId
     * @return
     */
    @Cacheable(value = "attr", key = "'attrInfo:'+#root.args[0]")
    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        // 1. 查询基本数据
        Attr attr = this.getById(attrId);

        // 2、查询关联数据
        if (attr != null) {
            AttrRespVo attrRespVo = AttrConvertMapper.INSTANCE.attrToAttrResponseVo(attr);

            // 添加分组ID和分组名称,基本属性才有分组信息
            if(attrRespVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
                AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.selectOne(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attrId));
                if (attrAttrgroupRelation != null) {
                    attrRespVo.setAttrGroupId(attrAttrgroupRelation.getAttrGroupId());
                    AttrGroup attrGroup = attrGroupMapper.selectOne(new QueryWrapper<AttrGroup>().eq("attr_group_id", attrAttrgroupRelation.getAttrGroupId()));
                    attrGroup.setAttrGroupName(attrGroup.getAttrGroupName());
                }
            }

            // 添加分类路径和分类名称
            attrRespVo.setCatelogPath(categoryService.findCatelogPath(attr.getCatelogId()));

            Category category = categoryMapper.selectById(attr.getCatelogId());
            if (category != null)
                attrRespVo.setCatelogName(category.getName());
            return attrRespVo;
        }
        return null;
    }

    /**
     * 更新属性
     *
     * @param attr
     */
    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        //1、修改基本属性
        Attr attrEntity = AttrConvertMapper.INSTANCE.attrVoToAttr(attr);
        this.updateById(attrEntity);

        //2、修改关联关系,基本属性才进行
        if(attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            AttrAttrgroupRelation relationEntity = new AttrAttrgroupRelation();
            relationEntity.setAttrId(attrEntity.getAttrId());
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            Long count = attrAttrgroupRelationMapper.selectCount(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attrEntity.getAttrId()));
            if (count > 0) {
                // 修改操作
                attrAttrgroupRelationMapper.update(relationEntity, new UpdateWrapper<AttrAttrgroupRelation>().eq("attr_id", attrEntity.getAttrId()));
            } else {
                // 新增操作
                attrAttrgroupRelationMapper.insert(relationEntity);
            }
        }

    }

    /**
     * 删除关联关系
     * @param attrGroupRelationVos
     */
    @Override
    public void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos) {
        // 批量删除
        List<Long> ids = Arrays.stream(attrGroupRelationVos)
                .flatMap(item -> attrAttrgroupRelationMapper.selectIdsByAttrGroupIdAndAttrId(item.getAttrGroupId(), item.getAttrId()).stream())
                .collect(Collectors.toList());

        // 通过关联表的ID 进行删除
        attrAttrgroupRelationMapper.deleteBatchIds(ids);
    }

    /**
     * 根据分组id获取没有关联的属性
     * @param params
     * @param attrgroupId
     * @return
     */
    @Override
    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId) {
        // 1、当前分组只能关联自己所属的分类里面的所有属性
        AttrGroup attrGroup = attrGroupMapper.selectById(attrgroupId);
        Long catelogId = attrGroup.getCatelogId();

        // 2、当前分组只能关联别的分组没有引用的属性
        // 2.1）、当前分类下的所有分组
        List<AttrGroup> attrGroups = attrGroupMapper.selectList(new QueryWrapper<AttrGroup>().eq("catelog_id", catelogId));

        // 2.2）、这些分组的关联属性
        List<Long> collect = attrGroups.stream().map((item) -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());
        List<AttrAttrgroupRelation> attrAttrgroupRelations = attrAttrgroupRelationMapper.selectList(new QueryWrapper<AttrAttrgroupRelation>().in("attr_group_id", collect));

        // 2.3）、从当前分类的所有属性中移除这些属性
        List<Long> attrIds = attrAttrgroupRelations.stream().map((item) -> {
            return item.getAttrId();
        }).collect(Collectors.toList());
        QueryWrapper<Attr> wrapper = new QueryWrapper<Attr>().eq("catelog_id", catelogId).eq("attr_type", ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        if (attrIds != null && attrIds.size() > 0) {
            wrapper.notIn("attr_id", attrIds);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<Attr> page = this.page(
                new Query<Attr>().getPage(params),
                wrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

    /**
     * 根据属性id获取可以检索的属性
     * @param attrIds
     * @return
     */
    @Override
    public List<Long> selectSearchAttrs(List<Long> attrIds) {
        QueryWrapper<Attr> wrapper = new QueryWrapper<Attr>().in("attr_id", attrIds).eq("search_type", 1);
        List<Attr> attrs = this.list(wrapper);
        return attrs.stream().map(Attr::getAttrId).toList();
    }
}
