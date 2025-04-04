package com.elon.hypesphere.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.elon.hypesphere.common.to.es.SkuEsModel;
import com.elon.hypesphere.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    private ElasticsearchClient esClient;

    /**
     * 保存商品到es
     * @param skuEsModels
     */
    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) {
        try {
            // 1. 构建批量操作集合（核心改进）
            List<BulkOperation> bulkOperations = skuEsModels.stream()
                    .map(model -> BulkOperation.of(op -> op
                            .index(idx -> idx
                                    .index("product")
                                    .id(model.getSkuId().toString())
                                    .document(model)
                            )
                    ))
                    .collect(Collectors.toList());

            // 2. 创建批量请求对象
            BulkRequest bulkRequest = BulkRequest.of(r -> r.operations(bulkOperations));

            // 3. 执行批量操作并获取响应
            BulkResponse response = esClient.bulk(bulkRequest);

            // 4. 错误处理
            if (response.errors()) {
                List<String> list = response.items().stream().map(BulkResponseItem::id).toList();
                log.error("商品上架错误: {}", list);
                return false;
            } else {
                log.info("成功批量插入{}条商品数据", skuEsModels.size());
                return true;
            }

        } catch (IOException e) {
            log.error("商品上架错误: {}", e.getMessage(), e);
            // TODO 可添加重试逻辑
            return false;
        }
    }
}
