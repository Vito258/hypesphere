package com.elon.hypesphere.product.service.impl;

import com.elon.hypesphere.product.entity.Category;
import com.elon.hypesphere.product.mapper.CategoryMapper;
import com.elon.hypesphere.product.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Override
    public List<Category> listWithTree() {
        // 1、查出所有分类
        List<Category> categoryList = baseMapper.selectList(null);
        // 2、组装成父子的树形结构
        // 2.1) 找到所有的一级分类
        List<Category> level1Menus = categoryList.stream().filter(category -> category.getParentCid() == 0).map((menu) -> {
                    menu.setChildren(getChildren(menu, categoryList));
                    return menu;
                }).sorted((menu1, menu2) -> {
                    return (menu1.getSort() == null? 0 : menu1.getSort()) - (menu2.getSort() == null? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());
        return level1Menus;
    }

    /**
     * 批量删除菜单
     * @param asList
     */
    @Override
    public void removeMenuByIds(List<Long> asList) {
       //TODO  1、检查当前要删除的菜单是否被其他地方引用
        baseMapper.deleteBatchIds(asList);
    }

    // 递归查找所有菜单的子菜单
    private List<Category> getChildren(Category root, List<Category> all) {
        List<Category> children = all.stream().filter(category -> category.getParentCid() == root.getCatId()).map(category -> {
            category.setChildren(getChildren(category, all));
            return category;
        }).sorted((menu1, menu2) -> {
            // 菜单的排序
            return (menu1.getSort() == null? 0 : menu1.getSort()) - (menu2.getSort() == null? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return children;
    }
}
