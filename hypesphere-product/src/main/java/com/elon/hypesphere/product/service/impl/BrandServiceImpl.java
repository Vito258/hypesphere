package com.elon.hypesphere.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.Query;
import com.elon.hypesphere.product.entity.Brand;
import com.elon.hypesphere.product.mapper.BrandMapper;
import com.elon.hypesphere.product.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 品牌 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        // 分页查询的方法
        String key = (String) params.get("key");
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            // 鍏抽敭瀛楅潪绌猴紝鎷兼帴鍏抽敭瀛楁煡璇㈡潯浠?
            queryWrapper.eq("brand_id", key).or().like("name", key);
        }
        IPage<Brand> page = this.page(
                new Query<Brand>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);

    }

    /**
     * 更新品牌信息
     *
     * @param brand
     */
    @Override
    public void updateDetail(Brand brand) {

    }
}
