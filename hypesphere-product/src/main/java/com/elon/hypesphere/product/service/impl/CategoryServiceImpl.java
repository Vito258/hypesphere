package com.elon.hypesphere.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elon.hypesphere.product.entity.Category;
import com.elon.hypesphere.product.mapper.CategoryMapper;
import com.elon.hypesphere.product.service.ICategoryBrandRelationService;
import com.elon.hypesphere.product.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elon.hypesphere.product.vo.Catalog2Vo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    private ICategoryBrandRelationService categoryBrandRelationService;

    /**
     * 查出所有分类，组装成父子的树形结构
     * @return
     */
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

    /**
     *  递归查找所有菜单的子菜单
     * @param root
     * @param all
     * @return
     */
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

    /**
     * 查找三级分类路径
     * @param catelogId
     * @return
     */
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> result =  findParent(catelogId,paths);
        Collections.reverse(result);
        return result.toArray(new Long[result.size()]);
    }

    /**
     * 查询夫分类的方法
     * @param catelogId
     * @param paths
     * @return
     */
    private List<Long> findParent(Long catelogId,List<Long> paths){
        //1、收集当前节点ID
        paths.add(catelogId);
        Category category = getById(catelogId);
        if(category.getParentCid() != 0) {
            findParent(category.getParentCid(),paths);
        }
        return paths;
    }

    /**
     * 修改菜单
     * @param category
     */
    @Override
    public void updateDetail(Category category) {
        // 1、更新当前菜单
        updateById(category);

        // 2、更新关联表中的信息
        if(!StringUtils.isEmpty(category.getName())){
            // 1、根据分类id修改品牌分类关联表中分类名称
            categoryBrandRelationService.update().eq("catelog_id", category.getCatId())
                    .set("catelog_name", category.getName())
                    .update(); // 必须调用此方法提交更新;
            // TODO 2、修改其他关联表中品牌名称
        }
    }

    /**
     * 查询一级分类
     * @return
     */
    @Override
    public List<Category> getLevel1Categories() {
        return baseMapper.selectList(new QueryWrapper<Category>().eq("cat_level", 1));
    }

    /**
     * 查询分类数据
     * @return
     */
    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        // 避免重复查询
        List<Category> categories = baseMapper.selectList(null);

        //1、查出所有1级分类
        List<Category> level1Categories = getParentCid(categories, 0L);
        Map<String, List<Catalog2Vo>> resultMap = level1Categories.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), l1 -> {
            // 2、封装1级分类下的二级分类
            List<Category> level2Categories = getParentCid(categories, l1.getCatId());

            // 封装上面的结果
            List<Catalog2Vo> catalog2Vos = new ArrayList<>();
            if (level2Categories != null) {
                catalog2Vos = level2Categories.stream().map(l2 -> {
                    // 3、封装2级分类下的三级分类
                    List<Category> level3Categories = getParentCid(categories, l2.getCatId());
                    List<Catalog2Vo.Catalog3Vo> catalog3Vos = level3Categories.stream().map(l3 -> new Catalog2Vo.Catalog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName())).toList();
                    return new Catalog2Vo(l1.getCatId().toString(), catalog3Vos, l2.getCatId().toString(), l2.getName());
                }).toList();
            }
            return catalog2Vos;
        }));
        return resultMap;
    }

    /**
     * 根据父分类id查询子分类
     * @param categories
     * @param parentCid
     */
    private List<Category> getParentCid(List<Category> categories, Long parentCid){
        return categories.stream().filter(category -> Objects.equals(category.getParentCid(), parentCid)).toList();
    }
}
