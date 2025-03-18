package com.elon.hypesphere.product.controller;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.product.entity.Category;
import com.elon.hypesphere.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * 查出所有分类以及子分类，以树形结构组装起来
     */
    @RequestMapping("/list/tree")
    public R list(){
        // 查出所有分类
        List<Category> categoryList = categoryService.listWithTree();
        return R.ok().put("data", categoryList);
    }

    /**
     * 根据id查询分类
     */
    @RequestMapping("/info")
    public R info(@RequestParam("catId") Long catId){
        Category category = categoryService.getById(catId);
        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Category category){
        categoryService.save(category);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Category category){
        categoryService.updateDetail(category);
        return R.ok();
    }

    /**
     * 批量删除菜单
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody List<Long> ids){
        categoryService.removeMenuByIds(ids);
        return R.ok();
    }

    /**
     * 批量更新排序
     */
    @RequestMapping("/update/sort")
    public R updateSort(@RequestBody List<Category> categoryList){
        categoryService.updateBatchById(categoryList);
        return R.ok();
    }

}
