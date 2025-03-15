package com.elon.hypesphere.product.controller;

import com.elon.hypesphere.common.utils.PageUtils;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.common.validator.group.AddGroup;
import com.elon.hypesphere.common.validator.group.UpdateGroup;
import com.elon.hypesphere.product.entity.Brand;
import com.elon.hypesphere.product.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody Brand brand/*, BindingResult result*/){
//        if (result.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            // 鑾峰彇鏍￠獙鐨勯敊璇粨鏋?
//            result.getFieldErrors().forEach((item) -> {
//                // 鑾峰彇鍒伴敊璇彁绀篎ieldError
//                String message = item.getDefaultMessage();
//                // 鑾峰彇閿欒鐨勫睘鎬у悕瀛?
//                String field = item.getField();
//                map.put(field, message);
//            });
//            return R.ok().error(400, "鎻愪氦鐨勬暟鎹笉鍚堟硶").put("data", map);
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
    public R update(@Validated({UpdateGroup.class}) @RequestBody Brand brand){
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
//
//    /**
//     * 鍒犻櫎
//     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Long[] brandIds){
//        brandService.removeByIds(Arrays.asList(brandIds));
//
//        return R.ok();
//    }


}
