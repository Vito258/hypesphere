package com.elon.hypesphere.search.vo;

import com.elon.hypesphere.common.to.es.SkuEsModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索返回结果
 */
@Data
public class SearchResult {
    // 商品信息
    private List<SkuEsModel> products;

    // 分页信息
    // 总记录数
    private Long total;

    // 当前页码
    private Integer pageNum;

    // 总页码
    private Integer totalPages;

    // 导航页码
    private List<Integer> pageNavs;

    // 面包屑导航数据
    private List<NavVo> navs = new ArrayList<>();

    // 当前查询属性，所有查询属性的id
    private List<Long> attrIds = new ArrayList<>();

    // 面包屑导航类
    @Data
    public static class NavVo {
      public String navName;
      public String navValue;
      public String link;
    }

    // 返回给页面的信息
    // 当前查询的结果，所有查询的品牌
    private List<BrandVo> brands;

    // 当前查询的结果，所有查询的属性
    private List<AttrVo> attrs;

    // 当前查询的结果，所有查询的分类
    private List<CatalogVo> catalogs;

    @Data
    public static class BrandVo{
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class AttrVo{
        private Long attrId;
        private String attrName;
        private List<String> attrValues;
    }

    @Data
    public static class CatalogVo{
        private Long catalogId;
        private String catalogName;
    }
}
