package com.elon.hypesphere.product.controller;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.product.service.IAttrService;
import com.elon.hypesphere.product.vo.AttrGroupRelationVo;
import com.elon.hypesphere.product.vo.AttrRespVo;
import com.elon.hypesphere.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据分类ID查询基本属性
     */
    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(@RequestParam() Map<String, Object> params,
                          @PathVariable("catelogId") Long catelogId,
                          @PathVariable("attrType") String type) {
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId, type);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
        AttrRespVo respVo = attrService.getAttrInfo(attrId);

        return R.ok().put("attr", respVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr){
        attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr){
        attrService.updateAttr(attr);

        return R.ok();
    }

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
