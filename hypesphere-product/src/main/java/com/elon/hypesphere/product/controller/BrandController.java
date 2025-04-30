package com.elon.hypesphere.product.controller;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.common.validator.group.AddGroup;
import com.elon.hypesphere.common.validator.group.UpdateGroup;
import com.elon.hypesphere.product.entity.Brand;
import com.elon.hypesphere.product.service.IBrandService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌 前端控制器
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    /**
     * 分页查询集合
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 根据Id查询
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
        Brand brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    @RequestMapping("/infos")
    public R infos(@RequestParam("brandIds") List<Long> brandIds){
        List<Brand> brandByIds = brandService.getBrandByIds(brandIds);
        return R.ok().put("brands", brandByIds);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody Brand brand /*,BindingResult bindingResult*/){
//        if (bindingResult.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            //1、获取校验的错误结果
//            bindingResult.getFieldErrors().forEach((item) -> {
//                // 2、获取到错误提示
//                String message = item.getDefaultMessage();
//                // 3、获取到错误属性的名称
//                String field = item.getField();
//                map.put(field, message);
//            });
//            return R.ok().error(500, "输入的信息不合法").put("data", map);
//        }else {
//            brandService.save(brand);
//            return R.ok();
//        }
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody Brand brand /*,BindingResult bindingResult*/){
//        if (bindingResult.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            //1、获取校验的错误结果
//            bindingResult.getFieldErrors().forEach((item) -> {
//                // 2、获取到错误提示
//                String message = item.getDefaultMessage();
//                // 3、获取到错误属性的名称
//                String field = item.getField();
//                map.put(field, message);
//            });
//            return R.ok().error(500, "输入的信息不合法").put("data", map);
//        }else {
//            brandService.updateDetail(brand);
//            return R.ok();
//        }
        brandService.updateDetail(brand);
        return R.ok();
    }

    /**
     * 修改显示状态
     */
    @RequestMapping("/update/status")
    public R updateStatus(@Validated(UpdateGroup.class) @RequestBody Brand brand){
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@Validated @RequestBody Long[] brandIds){
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }


}
