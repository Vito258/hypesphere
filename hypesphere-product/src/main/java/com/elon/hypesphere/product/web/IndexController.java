package com.elon.hypesphere.product.web;

import com.elon.hypesphere.product.entity.Category;
import com.elon.hypesphere.product.service.ICategoryService;
import com.elon.hypesphere.product.vo.Catalog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping({"/","index.html"})
    public String indexPage(Model model) {
        // 1、查出所有1级分类
        List<Category> categories = categoryService.getLevel1Categories();

        model.addAttribute("categories", categories);
        return "index";
    }

    @ResponseBody
    @RequestMapping("/index/catalog.json")
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        return categoryService.getCatalogJson();
    }
}
