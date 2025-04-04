package com.elon.hypesphere.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import co.elastic.clients.util.ObjectBuilder;
import com.elon.hypesphere.search.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.function.Function;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HypesphereSearchApplicationTests {

	@Autowired
	private ElasticsearchClient esClient;

	@Test
    public void contextLoads() {
		System.out.println("elasticsearchClient:" + esClient);
	}

	//1、测试创建索引
	@Test
	public void createIndex() throws Exception {
		esClient.indices().create(c -> c.index("products"));
	}
	//2、测试存储数据到elasticsearch
    @Test
	public void indexData() throws Exception {
		Product product = new Product();
		product.setId(1L);
		product.setName("华为手机");
		product.setPrice(2999.0);
		product.setDescription("华为手机，很便宜");
		product.setImages("http://www.baidu.com");

		IndexResponse response = esClient.index(i -> i
				.index("products")
				.id(product.getId().toString())
				.document(product));

		log.info("Indexed with version " + response.version());
    }

	//3、测试get查询数据
	@Test
	public void getData() throws Exception {
		GetResponse<Product> response = esClient.get(g -> g
						.index("products")
						.id("1"),
				Product.class
		);

		if (response.found()) {
			Product product = response.source();
			log.info("Product name： " + product.getName());
		} else {
			log.info ("Product not found");
		}
	}

	// 4、测试search查询文档
	@Test
	public void searchData() throws Exception {
		esClient.search(s -> s
						.index("product")
						.query(q -> q
								.match(t -> t
										.field("name")
										.query("华为手机")
								)
						),
				Product.class
		);
	}

	// 5、测试删除索引
	@Test
	public void deleteIndex() throws Exception {
		esClient.indices().delete(d -> d.index("products"));
	}

	// 6、测试删除数据
	@Test
	public void deleteData() throws Exception {
		esClient.delete(d -> d.index("products").id("1"));
	}

	// 7、测试更新数据
	@Test
	public void updateData() throws Exception {
		Product product = new Product();
		product.setId(1L);
		product.setName("华为手机");
		product.setPrice(2999.0);
		product.setDescription("华为手机，很便宜");
		product.setImages("http://www.baidu.com");
		esClient.index(i -> i
				.index("products")
				.id(product.getId().toString())
				.document(product));
	}

	// 8、测试复杂检索
	@Test
	public void complexSearch() throws Exception {
		String searchText = "华为";

		// 构建检索请求
		SearchResponse<Product> response = esClient.search(s -> s
						.index("products")
						.query(q -> q
								.match(t -> t
										.field("name")
										.query(searchText)
								)
						),
				Product.class
		);

		// 处理检索结果
		TotalHits total = response.hits().total();
		boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

		if (isExactResult) {
			log.info("There are " + total.value() + " results");
		} else {
			log.info("There are more than " + total.value() + " results");
		}

		List<Hit<Product>> hits = response.hits().hits();
		for (Hit<Product> hit: hits) {
			Product product = hit.source();
			log.info("Found product " + product.getId() + ", score " + hit.score());
		}
	}
}
