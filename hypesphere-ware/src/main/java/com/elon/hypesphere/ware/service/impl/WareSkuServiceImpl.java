package com.elon.hypesphere.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elon.hypesphere.common.to.SkuHasStockTo;
import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.Query;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.ware.entity.WareSku;
import com.elon.hypesphere.ware.feign.ProductFeignService;
import com.elon.hypesphere.ware.mapper.WareSkuMapper;
import com.elon.hypesphere.ware.service.IWareSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品库存 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSku> implements IWareSkuService {
    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSku> queryWrapper = new QueryWrapper<>();

        // skuId
        String skuId = (String) params.get("skuId");
        if (!StringUtils.isEmpty(skuId)) {
            queryWrapper.eq("sku_id", skuId);
        }

        // wareId
        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(wareId)) {
            queryWrapper.eq("ware_id", skuId);
        }

        IPage<WareSku> page = this.page(
                new Query<WareSku>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    /**
     * 添加库存
     *
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        QueryWrapper<WareSku> queryWrapper = new QueryWrapper<WareSku>()
                .eq("sku_id", skuId)
                .eq("ware_id", wareId);
        // 判断如果还没有这个库存记录，则新增
        if (this.list(queryWrapper).isEmpty()) {
            WareSku wareSku = new WareSku();
            wareSku.setSkuId(skuId);
            wareSku.setWareId(wareId);
            wareSku.setStock(skuNum);
            wareSku.setStockLocked(0);
            // TODO: 获取商品名称,如果失败整个事务不需要回滚
            try {
                R result = productFeignService.getSkuInfoBySkuId(skuId);
                if (result.getCode() == 0) {
                    Map<String, Object> skuInfo = (Map<String, Object>) result.get("skuInfo");
                    wareSku.setSkuName((String) skuInfo.get("skuName"));
                }
            } catch (Exception e) {

            }
            this.save(wareSku);
        } else {
            UpdateWrapper<WareSku> updateWrapper = new UpdateWrapper<WareSku>()
                    .eq("sku_id", skuId)
                    .eq("ware_id", wareId)
                    .setSql("stock = stock + " + skuNum);

            this.update(updateWrapper);
        }
    }

    /**
     * 查询skuId对应的库存信息
     * @param skuIds
     * @return
     */
    @Override
    public List<SkuHasStockTo> getSkusHasStock(List<Long> skuIds) {
        return skuIds.stream().map(skuId -> {
            SkuHasStockTo skuHasStockTo = new SkuHasStockTo();
            skuHasStockTo.setSkuId(skuId);
            skuHasStockTo.setHasStock(getAvailableStockBySkuId(skuId) > 0);
            return skuHasStockTo;
        }).toList();
    }

    /**
     * 根据skuId查询库存
     * @param skuId
     * @return
     */
    public Integer getAvailableStockBySkuId(Long skuId) {
        QueryWrapper<WareSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_id", skuId)
                .select("sum(stock - stock_locked) as available_stock");

        List<Map<String, Object>> result = baseMapper.selectMaps(queryWrapper);
        Map<String, Object> map = result.getFirst();
        if (map != null && !map.isEmpty()) {
            return (Integer) map.get("available_stock");
        }
        return 0;
    }
}
