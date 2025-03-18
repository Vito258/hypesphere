package com.elon.hypesphere.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.Query;
import com.elon.hypesphere.product.entity.Brand;
import com.elon.hypesphere.product.mapper.BrandMapper;
import com.elon.hypesphere.product.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elon.hypesphere.product.service.ICategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ICategoryBrandRelationService categoryBrandRelationService;

    /**
     * 分页查询
     *
     * @param params
     * sql select * from pms_brand where brand_id = ? or name like ?
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        // 分页查询的方法
        String key = (String) params.get("key");
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            // 根据key多字段模糊查询
            queryWrapper.eq("brand_id", key).or().like("name", key);
        }
        IPage<Brand> page = this.page(
                new Query<Brand>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);

    }

    /**
     * 更新品牌信息详情
     * @param brand
     */
    @Override
    public void updateDetail(Brand brand) {
        // 保证冗余字段的数据一致
        // 1、更新品牌信息
        brandMapper.updateById(brand);

        // 2、更新关联表中的信息
        if(!StringUtils.isEmpty(brand.getName())){
            // 1、根据品牌id修改品牌分类关联表中品牌名称
            categoryBrandRelationService.update().eq("brand_id", brand.getBrandId())
                    .set("brand_name", brand.getName())
                    .update(); // 必须调用此方法提交更新;
            // TODO 2、修改其他关联表中品牌名称
        }
    }
}
