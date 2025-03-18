package com.elon.hypesphere.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.Query;
import com.elon.hypesphere.product.entity.Attr;
import com.elon.hypesphere.product.mapper.AttrMapper;
import com.elon.hypesphere.product.service.IAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Attr> page = this.page(
                new Query<Attr>().getPage(params),
                new QueryWrapper<Attr>()
        );

        return new PageUtils(page);
    }
}
