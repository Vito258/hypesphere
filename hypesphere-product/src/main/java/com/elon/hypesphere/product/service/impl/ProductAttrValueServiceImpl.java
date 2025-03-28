package com.elon.hypesphere.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elon.hypesphere.product.entity.ProductAttrValue;
import com.elon.hypesphere.product.mapper.ProductAttrValueMapper;
import com.elon.hypesphere.product.service.IProductAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * spu属性值 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValue> implements IProductAttrValueService {

    /**
     * 获取spu规格，前端回显商品规格参数值
     *
     * @param spuId
     * @return
     */
    @Override
    public List<ProductAttrValue> baseAttrlistforspu(Long spuId) {
        QueryWrapper<ProductAttrValue> wrapper = new QueryWrapper<>();
        return list(wrapper.eq("spu_id", spuId));
    }

    /**
     * 修改商品规格
     *
     * @param spuId
     * @param entities
     */
    @Override
    public void updateSpuAttr(Long spuId, List<ProductAttrValue> entities) {
        //1.删除这个spuId之前对应的所有属性
        this.remove(new QueryWrapper<ProductAttrValue>().eq("spu_id", spuId));
        //2.插入修改后的数据
        List<ProductAttrValue> collect = entities.stream().map((item) -> {
            item.setSpuId(spuId);
            return item;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }
}
