package com.elon.hypesphere.search.controller;

import com.elon.hypesphere.search.service.SearchService;
import com.elon.hypesphere.search.vo.SearchParam;
import com.elon.hypesphere.search.vo.SearchResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    /**
     * 处理检索功能
     * @return
     */
    @RequestMapping("/list.html")
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {

        String queryString = request.getQueryString();
        param.set_queryString(queryString);

        SearchResult result = searchService.search(param);

        model.addAttribute("result", result);
        return "list";
    }
}
