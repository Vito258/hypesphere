package com.elon.hypesphere.search.vo;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 封装页面传递过来的所有查询条件
 */
@Data
public class SearchParam {
    // 搜索关键字
    private String keyword;

    // 三级分类id
    private Long catalog3Id;

    // 排序条件
    private String sort;

    // 是否有货
    private Integer hasStock;

    // 价格区间
    private String skuPrice;

    // 品牌Id
    private List<Long> brandIds;

    // 属性条件
    private  List<String> attrs;

    // 页码
    private Integer pageNum = 1;

    // 请求url
    private String  _queryString;
}
