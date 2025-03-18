package com.elon.hypesphere.product.controller;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.product.entity.AttrGroup;
import com.elon.hypesphere.product.service.IAttrAttrgroupRelationService;
import com.elon.hypesphere.product.service.IAttrGroupService;
import com.elon.hypesphere.product.service.IAttrService;
import com.elon.hypesphere.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 属性分组 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/attrgroup")
public class AttrGroupController {
    @Autowired
    private IAttrGroupService attrGroupService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    IAttrService attrService;
    @Autowired
    IAttrAttrgroupRelationService relationService;

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("catelogId") Long catelogId) {
        PageUtils page = attrGroupService.queryPage(params, catelogId);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroup attrGroup = attrGroupService.getById(attrGroupId);

        // 查询三级分类路径
        Long[] path = categoryService.findCatelogPath(attrGroup.getCatelogId());
        attrGroup.setCatelogPath(path);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroup attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroup attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

}
