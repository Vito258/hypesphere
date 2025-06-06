package com.elon.hypesphere.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.Query;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.product.entity.Attr;
import com.elon.hypesphere.product.entity.AttrGroup;
import com.elon.hypesphere.product.mapper.AttrConvertMapper;
import com.elon.hypesphere.product.mapper.AttrGroupMapper;
import com.elon.hypesphere.product.service.IAttrAttrgroupRelationService;
import com.elon.hypesphere.product.service.IAttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elon.hypesphere.product.service.IAttrService;
import com.elon.hypesphere.product.service.ICategoryService;
import com.elon.hypesphere.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 属性分组 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements IAttrGroupService {
    @Autowired
    IAttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroup> page = this.page(
                new Query<AttrGroup>().getPage(params),
                new QueryWrapper<AttrGroup>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据catelogId查询
     *
     * @param params
     * @param catelogId
     * @sql select * from pms_attr_group where catelog_id = ? and (attr_group_id = ? or attr_group_name like ?)
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        // 画面输入关键字
        String key = (String) params.get("key");
        QueryWrapper<AttrGroup> wrapper = new QueryWrapper<AttrGroup>();
        if (!StringUtils.isEmpty(key)) {
            // 根据key多字段模糊查询
            // and (() or ())
            wrapper.and((obj) -> {
                obj.eq("attr_group_id", key).or().like("attr_group_name", key);
            });
        }
        if (catelogId == 0) {
            // 查询所有
            IPage<AttrGroup> page = this.page(
                    new Query<AttrGroup>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        } else {
            // 根据catelogId查询
            wrapper.eq("catelog_id", catelogId);
            IPage<AttrGroup> page = this.page(
                    new Query<AttrGroup>().getPage(params),
                    wrapper
            );
            return new PageUtils(page);
        }
    }

    /**
     * 根据catelogId查询当前分类下的所有分组以及分组下的所有属性信息
     * @param catlogId
     * @return
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catlogId) {
        // 1、查询分组信息
        List<AttrGroup> attrGroups = list(new QueryWrapper<AttrGroup>().eq("catelog_id", catlogId));

        // 2、查询所有属性
        List<AttrGroupWithAttrsVo> collect = attrGroups.stream().map((item) -> {
            AttrGroupWithAttrsVo attrGroupWithAttrsVo = AttrConvertMapper.INSTANCE.attrGroupToAttrGroupWithAttrsVo(item);
            List<Attr> attrs = attrService.getRelationAttr(attrGroupWithAttrsVo.getAttrGroupId());
            attrGroupWithAttrsVo.setAttrs(attrs);
            return attrGroupWithAttrsVo;
        }).collect(Collectors.toList());

        return collect;
    }
}
