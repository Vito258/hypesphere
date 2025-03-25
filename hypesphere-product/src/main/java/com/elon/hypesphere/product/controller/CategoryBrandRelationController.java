package com.elon.hypesphere.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.product.entity.Brand;
import com.elon.hypesphere.product.entity.CategoryBrandRelation;
import com.elon.hypesphere.product.service.ICategoryBrandRelationService;
import com.elon.hypesphere.product.vo.BrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 品牌分类关联 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private ICategoryBrandRelationService categoryBrandRelationService;

    /**
     * 列表
     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = categoryBrandRelationService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }

    /**
     * 查询品牌关联的所有分类列表
     */
    @GetMapping("/catelog/list")
    public R cateloglist(@RequestParam("brandId") Long brandId){
        List<CategoryBrandRelation> data = categoryBrandRelationService.list(
                new QueryWrapper<CategoryBrandRelation>().eq("brand_id", brandId));

        return R.ok().put("data", data);
    }

    /**
     * 获取分类关联的所有品牌
     * 1、controller：处理请求，接收和校验数据【JSR303】
     * 2、service接收controller传来的数据，进行业务处理
     * 3、controller接收service处理完的数据，封装页面指定的vo
     */
    @GetMapping("/brands/list")
    public R relationBrandList(@RequestParam(value = "catId", required = true) Long catId){
        List<Brand> brandEntities = categoryBrandRelationService.getBrandsByCatId(catId);
        List<BrandVo> data = brandEntities.stream().map(item -> {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(item.getBrandId());
            brandVo.setBrandName(item.getName());
            return brandVo;
        }).collect(Collectors.toList());

        return R.ok().put("data", data);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 新增品牌与分类的关联关系
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryBrandRelation categoryBrandRelation){
        categoryBrandRelationService.saveDetail(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryBrandRelation categoryBrandRelation){
        categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
