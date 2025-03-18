package com.elon.hypesphere.product.controller;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.product.entity.ProductAttrValue;
import com.elon.hypesphere.product.service.IAttrService;
import com.elon.hypesphere.product.service.IProductAttrValueService;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/attr")
public class AttrController {
    @Autowired
    private IAttrService attrService;

    @Autowired
    private IProductAttrValueService productAttrValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
//    @RequestMapping("/info/{attrId}")
//    public R info(@PathVariable("attrId") Long attrId){
//        AttrRespVO respVo = attrService.getAttrInfo(attrId);
//
//        return R.ok().put("attr", respVo);
//    }

    /**
     * 保存
     */
//    @RequestMapping("/save")
//    public R save(@RequestBody AttrVO attr){
//        attrService.save(attr);
//
//        return R.ok();
//    }

    /**
     * 修改
     */
//    @RequestMapping("/update")
//    public R update(@RequestBody AttrVO attr){
//        attrService.updateAttr(attr);
//
//        return R.ok();
//    }

    /**
     * 修改商品规格
     */
//    @PostMapping("/update/{spuId}")
//    public R updateSpuAttr(@PathVariable("spuId") Long spuId, @RequestBody List<ProductAttrValue> entities){
//        productAttrValueService.updateSpuAttr(spuId, entities);
//
//        return R.ok();
//    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
