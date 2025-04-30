package com.elon.hypesphere.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elon.hypesphere.product.entity.Category;
import com.elon.hypesphere.product.mapper.CategoryMapper;
import com.elon.hypesphere.product.service.ICategoryBrandRelationService;
import com.elon.hypesphere.product.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elon.hypesphere.product.vo.Catalog2Vo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 查出所有分类，组装成父子的树形结构
     *
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
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());
        return level1Menus;
    }

    /**
     * 批量删除菜单
     *
     * @param asList
     */
    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO  1、检查当前要删除的菜单是否被其他地方引用
        baseMapper.deleteBatchIds(asList);
    }

    /**
     * 递归查找所有菜单的子菜单
     *
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
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return children;
    }

    /**
     * 查找三级分类路径
     *
     * @param catelogId
     * @return
     */
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> result = findParent(catelogId, paths);
        Collections.reverse(result);
        return result.toArray(new Long[result.size()]);
    }

    /**
     * 查询夫分类的方法
     *
     * @param catelogId
     * @param paths
     * @return
     */
    private List<Long> findParent(Long catelogId, List<Long> paths) {
        //1、收集当前节点ID
        paths.add(catelogId);
        Category category = getById(catelogId);
        if (category.getParentCid() != 0) {
            findParent(category.getParentCid(), paths);
        }
        return paths;
    }

    /**
     * 修改菜单
     *
     * @param category
     * @CacheEvict：失效模式
     */
    @Transactional
//    @Caching(evict = {
//            @CacheEvict(value = {"category"}, key = "'getLevel1Categories'"),
//            @CacheEvict(value = {"category"}, key = "'getCatalogJson'")
//    })
    @CacheEvict(value = {"category"}, allEntries = true)
    @Override
    public void updateDetail(Category category) {
        // 1、更新当前菜单
        updateById(category);

        // 2、更新关联表中的信息
        if (!StringUtils.isEmpty(category.getName())) {
            // 1、根据分类id修改品牌分类关联表中分类名称
            categoryBrandRelationService.update().eq("catelog_id", category.getCatId())
                    .set("catelog_name", category.getName())
                    .update(); // 必须调用此方法提交更新;
            // TODO 2、修改其他关联表中品牌名称
        }
    }

    /**
     * 查询一级分类
     *
     * @return
     */
    @Cacheable(value = {"category"},
            key = "#root.methodName",
            sync = true
    )
    // 代表当前方法的结果需要缓存，如果缓存中有那么不需要调用方法，如果缓存中没有那么就会调用方法并将结果放入缓存
    // 以方法名称为key，方法返回值作为value
    @Override
    public List<Category> getLevel1Categories() {
        return baseMapper.selectList(new QueryWrapper<Category>().eq("cat_level", 1));
    }

    /**
     * 查询三级分类-最终版本
     *
     * @return
     */
    @Cacheable(value = {"category"}, key = "#root.methodName")
    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        // 避免重复查询
        List<Category> categories = baseMapper.selectList(null);
        System.out.println("查询了数据库。。。。");
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
     * 查询三级分类-缓存加锁
     *
     * @return
     */
    public Map<String, List<Catalog2Vo>> getCatalogJson2() {
        // 先从redis中获取分类数据
        String catalogJson = stringRedisTemplate.opsForValue().get("catalogJson");

        if (org.springframework.util.StringUtils.hasLength(catalogJson)) {
            // 如果存在，直接返回
            System.out.println("缓存命中，查询缓存....");
            try {
                // 将json字符串转换为对象
                return new ObjectMapper().readValue(catalogJson, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            // 如果不存在，查出数据库，并放入redis中，然后返回
            System.out.println("缓存未命中，查询数据库....");
            return getCatalogJsonFromDBWithRedisLock();
        }
    }

    /**
     * 使用本地锁获取分类数据
     *
     * @return
     */
    public Map<String, List<Catalog2Vo>> getCatalogJsonFromDBWithLocalLock() {
        // 加入本地锁
        synchronized (this) {
            return getCatalogFromDB();
        }
    }

    /**
     * 使用分布式锁获取分类数据
     */
    public Map<String, List<Catalog2Vo>> getCatalogJsonFromDBWithRedisLock() {
        // 占分布式锁
        String uuid = UUID.randomUUID().toString();
        Boolean isLock = stringRedisTemplate.opsForValue().setIfAbsent("lock", uuid, 30, TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(isLock)) {
            // 加锁成功
            // 删除锁
            String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
            stringRedisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), List.of("lock"), uuid);
            return getCatalogFromDB();
        } else {
            try {
                // 休眠100ms
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("线程sleep出现错误，{}", e);
            }
            // 加锁失败，使用自旋的方式重试
            return getCatalogJsonFromDBWithRedisLock();
        }
    }

    /**
     * 使用redisson获取分类数据
     *
     * @return
     */
    public Map<String, List<Catalog2Vo>> getCatalogJsonFromDBWithRedissonLock() {
        // 占分布式锁
        RLock lock = redissonClient.getLock("catalogJson-lock");
        lock.lock();
        Map<String, List<Catalog2Vo>> catalogFromDB = Map.of();
        try {
            catalogFromDB = getCatalogFromDB();
        } catch (Exception e) {
            log.error("从数据库中获取分类信息失败", e);
        } finally {
            lock.unlock();
        }
        return catalogFromDB;

    }

    /**
     * 从Mysql获取分类数据
     *
     * @return
     */
    private Map<String, List<Catalog2Vo>> getCatalogFromDB() {
        // 得到锁之后应该再去缓存中确定一次，如果没有再继续查询先从redis中获取分类数据
        String catalogJson = stringRedisTemplate.opsForValue().get("catalogJson");
        // 如果缓存中有数据直接返回缓存中的结果
        if (org.springframework.util.StringUtils.hasLength(catalogJson)) {
            // 如果存在，直接返回
            try {
                // 将json字符串转换为对象
                System.out.println("二次检查缓存命中，查询缓存....");
                return new ObjectMapper().readValue(catalogJson, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            // 避免重复查询
            List<Category> categories = baseMapper.selectList(null);
            System.out.println("查询了数据库。。。。");
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

            // 将数据放入redis中
            try {
                // 将对象转为Json字符串
                ObjectMapper mapper = new ObjectMapper();
                // java对象转换为json字符串
                String catalogJsonFromDBString = mapper.writeValueAsString(resultMap);
                stringRedisTemplate.opsForValue().set("catalogJson", catalogJsonFromDBString, 1, TimeUnit.DAYS);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return resultMap;
        }
    }

    /**
     * 根据父分类id查询子分类
     *
     * @param categories
     * @param parentCid
     */
    private List<Category> getParentCid(List<Category> categories, Long parentCid) {
        return categories.stream().filter(category -> Objects.equals(category.getParentCid(), parentCid)).toList();
    }
}
