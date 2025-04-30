package com.elon.hypesphere.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.json.JsonData;
import com.elon.hypesphere.common.constant.EsConstant;
import com.elon.hypesphere.common.to.es.SkuEsModel;
import com.elon.hypesphere.common.utils.R;
import com.elon.hypesphere.search.feign.ProductFeignService;
import com.elon.hypesphere.search.service.SearchService;
import com.elon.hypesphere.search.vo.AttrResponseVo;
import com.elon.hypesphere.search.vo.BrandVo;
import com.elon.hypesphere.search.vo.SearchParam;
import com.elon.hypesphere.search.vo.SearchResult;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ElasticsearchClient esClient;

    @Autowired
    private ProductFeignService productFeignService;

    /**
     * 检索功能
     * 返回页面需要的所有信息
     *
     * @param param
     * @return
     */
    @Override
    public SearchResult search(SearchParam param) {
        //动态构建出需要查询的Dsl语句
        SearchResult result = new SearchResult();
        // 1、准备检索请求
        SearchRequest searchRequest = buildSearchRequest(param);
        // 2、执行检索请求
        SearchResponse<SkuEsModel> response = null;
        System.out.println("searchRequest:" + searchRequest);
        try {
//            response = esClient.search(searchRequest, SkuEsModel.class);
            SearchRequest request = SearchRequest.of(s -> s
                    .index(EsConstant.PRODUCT_INDEX)
                    .query(q -> q
                            .bool(b -> b
                                    .must(m -> m
                                            .match(match -> match
                                                    .field("skuTitle")
                                                    .query(param.getKeyword())
                                            )
                                    )
                            )
                    )
            );
            response = esClient.search(request, SkuEsModel.class);
        } catch (IOException e) {
            log.error("检索失败");
        }

        // 3、分析相应数据，封装成需要的格式
        return buildSearchResult(response, param);
    }

    /**
     * 准备检索请求
     *
     * @return
     */
    private SearchRequest buildSearchRequest(SearchParam param) {
        // 构建dsl语句
        SearchRequest.Builder builder = new SearchRequest.Builder();
        // 查询（模糊匹配、过滤）
        BoolQuery.Builder boolQuery = new BoolQuery.Builder();

        // 1.1 must 模糊匹配
        if (!StringUtils.isEmpty(param.getKeyword())) {
            boolQuery.must(q -> q.match(m ->
                    m.field("skuTitle")
                            .query(param.getKeyword())
            ));
        }

        // 1.2 filter
        // 分类Id
        if (param.getCatalog3Id() != null) {
            boolQuery.filter(fq -> fq.term(t -> t.field("catalogId").value(param.getCatalog3Id())));
        }
        // 品牌Id
        if (param.getBrandIds() != null && !param.getBrandIds().isEmpty()) {
            boolQuery.filter(fq -> fq.term(t -> t.field("brandId").value(param.getBrandIds().getFirst())));
        }
        // 指定属性
        if (param.getAttrs() != null && !param.getAttrs().isEmpty()) {
            // 遍历所有属性使用nest嵌入式查询
            for (String attr : param.getAttrs()) {
                String[] s = attr.split("_");
                String attrId = s[0];
                String[] attrValues = s[1].split(":");

                // 创建嵌套查询的 Query 对象
                Query nestedQuery = Query.of(q -> q
                        .bool(b -> b
                                .must(m -> m
                                        .term(t -> t
                                                .field("attrs.attrId")
                                                .value(attrId)
                                        )
                                )
                                .must(m -> m
                                        .terms(t -> t
                                                .field("attrs.attrValue")
                                                .terms(ts -> ts
                                                        .value(
                                                                Arrays.stream(attrValues)
                                                                        .map(FieldValue::of)
                                                                        .collect(Collectors.toList())
                                                        )
                                                )
                                        )
                                )
                        )
                );
                // 添加嵌套过滤条件
                boolQuery.filter(f -> f
                        .nested(n -> n
                                .path("attrs")
                                .query(nestedQuery)
                        )
                );
            }
        }
        // 是否有库存
        if (param.getHasStock() != null) {
            boolQuery.filter(fq -> fq.term(t -> t.field("hasStock").value(param.getHasStock() == 1)));
        }
        // 价格区间
        if (!StringUtils.isEmpty(param.getSkuPrice())) {
            // 1.2.1 skuPrice
            String[] s = param.getSkuPrice().split("_");
            if (s.length == 2)
                // 0_500 的情况
                boolQuery.filter(fq -> fq.range(r -> r.field("skuPrice").gte(JsonData.of(Float.parseFloat(s[0]))).lte(JsonData.of(Float.parseFloat(s[1])))));
            else if (s.length == 1) {
                if (param.getSkuPrice().startsWith("_")) {
                    // _500 的情况
                    boolQuery.filter(fq -> fq.range(r -> r.field("skuPrice").lte(JsonData.of(s[0]))));
                } else if (param.getSkuPrice().endsWith("_")) {
                    // 500_ 的情况
                    boolQuery.filter(fq -> fq.range(r -> r.field("skuPrice").gte(JsonData.of(s[0]))));
                }

            }
        }
        // 设置查询条件
        builder.query(q -> q.bool(boolQuery.build()));

        // 排序、分页、高亮
        // 排序
        if (!StringUtils.isEmpty(param.getSort())) {
            String[] s = param.getSort().split("_");
            String name = s[0];
            SortOrder order = "asc".equalsIgnoreCase(s[1]) ? SortOrder.Asc : SortOrder.Desc;
            builder.sort(srt -> srt.field(fld -> fld.field(name).order(order)));
        }

        // 分页
        builder.from((param.getPageNum() - 1) * EsConstant.PRODUCT_PAGESIZE);
        builder.size(EsConstant.PRODUCT_PAGESIZE);

        // 高亮
        if (!StringUtils.isEmpty(param.getKeyword())) {
            builder.highlight(hl -> hl.fields("skuTitle", hf -> hf.preTags("<b style='color:red'>").postTags("</b>")));
        }

        // 聚合
        // 1 品牌聚合
        builder.aggregations("brand_agg", a -> a
                .terms(t -> t
                        .field("brandId")
                        .size(50)
                )
                .aggregations("brand_name_agg", a2 -> a2
                        .terms(t -> t
                                .field("brandName.keyword")
                                .size(1)
                        )
                )
                .aggregations("brand_img_agg", a2 -> a2
                        .terms(t -> t
                                .field("brandImg")
                                .size(1)
                        )
                )
        );

        // 2 分类聚合
        builder.aggregations("catalog_agg", a -> a
                .terms(t -> t
                        .field("catalogId")
                        .size(20)
                )
                .aggregations("catalog_name_agg", a2 -> a2
                        .terms(t -> t
                                .field("catalogName.keyword")
                                .size(1)
                        )
                )
        );

        // 3 属性聚合
        builder.aggregations("attr_agg", a -> a
                .nested(n -> n
                        .path("attrs")
                )
                .aggregations("attr_id_agg", a2 -> a2
                        .terms(t -> t
                                .field("attrs.attrId")
                        )
                        .aggregations("attr_name_agg", a3 -> a3
                                .terms(t -> t
                                        .field("attrs.attrName.keyword")
                                        .size(1)
                                )
                        )
                        .aggregations("attr_value_agg", a3 -> a3
                                .terms(t -> t
                                        .field("attrs.attrValue.keyword")
                                        .size(50)
                                )
                        )
                )
        );
        return builder.index(EsConstant.PRODUCT_INDEX).build();
    }

    /**
     * 构建结果数据
     *
     * @param response
     * @return
     */
    private SearchResult buildSearchResult(SearchResponse response, SearchParam param) {
        SearchResult result = new SearchResult();

        // 获取命中的记录
        HitsMetadata<SkuEsModel> hits = response.hits();

        // 1. 返回的所有商品信息
        List<SkuEsModel> products = new ArrayList<>();
        if (hits != null && hits.hits() != null) {
            for (Hit<SkuEsModel> hit : hits.hits()) {
                // 提取Product并检查空值
                SkuEsModel product = hit.source();
                // 替换高亮文本
                if (StringUtils.isEmpty(param.getKeyword())) {
                    List<String> skuTitle = hit.highlight().get("skuTitle");
                    String s = skuTitle.getFirst();
                    product.setSkuTitle(s);
                }

                if (product != null) {
                    products.add(product);
                }
            }
        }
        result.setProducts(products);

        // 获取聚合结果的Map结构
        Map<String, Aggregate> aggregations = response.aggregations();
        if (aggregations != null) {
            // 1. 处理属性聚合
            List<SearchResult.AttrVo> attrVos = new ArrayList<>();
            Aggregate attrAgg = aggregations.get("attr_agg");
            if (attrAgg != null && attrAgg.isNested()) {
                NestedAggregate nestedAttrAgg = attrAgg.nested();

                // 2. 获取属性ID聚合
                Aggregate attrIdAgg = nestedAttrAgg.aggregations().get("attr_id_agg");
                if (attrIdAgg != null && attrIdAgg.isLterms()) { // 长整型terms聚合
                    LongTermsAggregate attrIdTerms = attrIdAgg.lterms();

                    // 3. 遍历属性ID桶
                    for (LongTermsBucket bucket : attrIdTerms.buckets().array()) {
                        SearchResult.AttrVo attrVo = new SearchResult.AttrVo();

                        // 设置属性ID
                        attrVo.setAttrId(bucket.key());

                        // 4. 处理属性名称子聚合
                        Aggregate nameAgg = bucket.aggregations().get("attr_name_agg");
                        if (nameAgg != null && nameAgg.isSterms()) {
                            StringTermsAggregate nameTerms = nameAgg.sterms();
                            if (!nameTerms.buckets().array().isEmpty()) {
                                attrVo.setAttrName(
                                        nameTerms.buckets().array().get(0).key().stringValue()
                                );
                            }
                        }

                        // 5. 处理属性值子聚合
                        Aggregate valueAgg = bucket.aggregations().get("attr_value_agg");
                        if (valueAgg != null && valueAgg.isSterms()) {
                            StringTermsAggregate valueTerms = valueAgg.sterms();
                            List<String> values = valueTerms.buckets().array()
                                    .stream()
                                    .map(b -> b.key().stringValue())
                                    .collect(Collectors.toList());
                            attrVo.setAttrValues(values);
                        }

                        attrVos.add(attrVo);
                    }
                }
            }
            result.setAttrs(attrVos);

            // 2. 处理品牌聚合
            List<SearchResult.BrandVo> brandVos = new ArrayList<>();
            Aggregate brandAgg = aggregations.get("brand_agg");

            if (brandAgg != null && brandAgg.isSterms()) {
                StringTermsAggregate brandTerms = brandAgg.sterms();
                for (StringTermsBucket bucket : brandTerms.buckets().array()) {
                    SearchResult.BrandVo brandVo = new SearchResult.BrandVo();
                    try {
                        // 转换品牌ID
                        long brandId = Long.parseLong(bucket.key().stringValue());
                        brandVo.setBrandId(brandId);

                        // 处理品牌名称
                        Aggregate brandNameAgg = bucket.aggregations().get("brand_name_agg");
                        StringTermsAggregate brandNameTerms = brandNameAgg.sterms();
                        if (!brandNameTerms.buckets().array().isEmpty()) {
                            brandVo.setBrandName(brandNameTerms.buckets().array().get(0).key().stringValue());
                        }

                        // 处理品牌图片
                        Aggregate brandImgAgg = bucket.aggregations().get("brand_img_agg");
                        StringTermsAggregate brandImgTerms = brandImgAgg.sterms();
                        if (!brandImgTerms.buckets().array().isEmpty()) {
                            brandVo.setBrandImg(brandImgTerms.buckets().array().get(0).key().stringValue());
                        }

                        brandVos.add(brandVo);
                    } catch (NumberFormatException e) {
                        log.error("Invalid brand ID: {}", bucket.key().stringValue());
                    }
                }
            }
            result.setBrands(brandVos);

            // 3. 处理分类聚合
            List<SearchResult.CatalogVo> catalogVos = new ArrayList<>();
            Aggregate catalogAgg = aggregations.get("catalog_agg");

            if (catalogAgg != null && catalogAgg.isSterms()) {
                StringTermsAggregate catalogTerms = catalogAgg.sterms();

                for (StringTermsBucket bucket : catalogTerms.buckets().array()) {
                    SearchResult.CatalogVo catalogVo = new SearchResult.CatalogVo();
                    catalogVo.setCatalogId(Long.parseLong(bucket.key().stringValue()));

                    // 处理分类名称
                    Aggregate catalogNameAgg = bucket.aggregations().get("catalog_name_agg");
                    StringTermsAggregate catalogNameTerms = catalogNameAgg.sterms();
                    if (!catalogNameTerms.buckets().array().isEmpty()) {
                        catalogVo.setCatalogName(catalogNameTerms.buckets().array().get(0).key().stringValue());
                    }
                    catalogVos.add(catalogVo);
                }
            }

            result.setCatalogs(catalogVos);
        }

        // 5. 分页信息-页码
        result.setPageNum(param.getPageNum());
        // 6. 分页信息-总记录数
        assert hits.total() != null;
        result.setTotal(hits.total().value());
        // 7. 分页信息-总页码
        result.setTotalPages((int) Math.ceil(hits.total().value() % EsConstant.PRODUCT_PAGESIZE == 0 ? hits.total().value() / EsConstant.PRODUCT_PAGESIZE : hits.total().value() / EsConstant.PRODUCT_PAGESIZE + 1));

        // 8. 添加导航页码
        List<Integer> pageNavs = result.getPageNavs();
        if (pageNavs == null) {
            pageNavs = new ArrayList<>();
            result.setPageNavs(pageNavs); // 确保 pageNavs 不为 null
        }

        for (int i = 1; i <= result.getTotalPages(); i++) {
            pageNavs.add(i);
        }

        // 9. 构建面包屑导航功能
        if (param.getAttrs() != null && !param.getAttrs().isEmpty()) {
            List<SearchResult.NavVo> navVos = new ArrayList<>();
            List<SearchResult.NavVo> list = param.getAttrs().stream().map(item -> {
                SearchResult.NavVo navVo = new SearchResult.NavVo();
                String[] attrs = item.split("_");
                R attrsInfo = productFeignService.getAttrsInfo(Long.parseLong(attrs[0]));
                result.getAttrIds().add(Long.parseLong(attrs[0]));
                if (attrsInfo.getCode() == 0) {
                    AttrResponseVo attr = attrsInfo.getData("attr", new TypeReference<AttrResponseVo>() {
                    });
                    navVo.setNavName(attr.getAttrName());
                } else {
                    navVo.setNavName(attrs[0]);
                }
                navVo.setNavValue(attrs[1]);

                // 取消面包屑之后跳转到哪里，将请求地址上的url 参数移除
                String replaceQueryString = replaceQueryString(param, item,"attrs");
                navVo.setLink("http://search.hypesphere.com/list.html?" + replaceQueryString);
                return navVo;
            }).toList();
            result.setNavs(navVos);
        }

        // 品牌、分类
        if (param.getBrandIds() != null && !param.getBrandIds().isEmpty()) {
            List<SearchResult.NavVo> navs = result.getNavs();
            SearchResult.NavVo navVo = new SearchResult.NavVo();
            navVo.setNavName("品牌");
            // 远程查询所有品牌
            R r = productFeignService.brandsInfo(param.getBrandIds());
            if(r.getCode() == 0){
                List<BrandVo> brands = r.getData("brands", new TypeReference<List<BrandVo>>() {});
                StringBuffer buffer = new StringBuffer();
                String replace = "";
                for(BrandVo brand : brands){
                    buffer.append(brand.getBrandName()+";");
                    replace = replaceQueryString(param, brand.getBrandId()+"","brandId");
                }
                navVo.setNavValue(buffer.toString());
                navVo.setLink("http://search.hypesphere.com/list.html?" + replace);
            }
            navs.add(navVo);
        }
        return result;
    }

    /**
     * 替换URL中的参数
     * @param param
     * @param value
     * @return
     */
    private static String replaceQueryString(SearchParam param, String value,String key) {
        String encode = "";
        try {
            encode = URLEncoder.encode(value, "UTF-8");
            // 浏览器对空格编码和java 不同
            encode = encode.replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String replaceQueryString = param.get_queryString().replace("&"+key+"=" + encode, "");
        return replaceQueryString;
    }
}
