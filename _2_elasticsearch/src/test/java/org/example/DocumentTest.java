package org.example;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SearchType;
import co.elastic.clients.elasticsearch._types.StoredScript;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.JsonpMapper;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import junit.framework.TestCase;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.example.entity.Product;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * @author hongchuzhen
 * @date 12/21/2023
 */
public class DocumentTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DocumentTest(String testName) {
        super(testName);
    }

    /**
     * 每个测试方法执行前执行
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        System.out.println("---------------- start test ---------------");
        super.setUp();
        this.testGetClient();

    }

    /**
     * 每个测试方法执行后执行
     *
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.out.println("------------------- end test -----------------");
    }


    /**
     * 同步的 es 客户端
     */
    ElasticsearchClient client;

    public static String INDEX_NAME = "products";

    /**
     * 连接客户端
     *
     * @return
     * @throws IOException
     */
    public void testGetClient() throws IOException {
        // Create the low-level client
        RestClient restClient = RestClient
                .builder(new HttpHost("localhost", 5302))
                .build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the block API client
        client = new ElasticsearchClient(transport);

    }

    /**
     * 创建索引
     *
     * @throws IOException
     */
    public void testCreateIndex() throws IOException {
        CreateIndexResponse response = client.indices().create(c -> c.index(INDEX_NAME));
        boolean acknowledged = response.acknowledged();
        boolean shardsAcknowledged = response.shardsAcknowledged();
        String index = response.index();

        System.out.format("创建索引状态%s\n", acknowledged);
        System.out.format("已确认的分片%s\n", shardsAcknowledged);
        System.out.format("索引名称%s\n", index);
    }

    Random random = new Random();

    /**
     * 创建文档 - 方式1
     */
    public void testAddDocument01() throws IOException {
        Product cityBike = new Product("city bike", "bk-1", 123.0);

        IndexRequest.Builder<Product> productBuilder = new IndexRequest.Builder<>();
        productBuilder.index(INDEX_NAME)
                .id(cityBike.getSku() + random.nextInt())
                .document(cityBike);

        IndexResponse response = client.index(productBuilder.build());
        System.out.println(response.index());
        System.out.println(response);
    }

    /**
     * 创建文档 - 方式2
     */
    public void testAddDocument02() throws IOException {
        // 文档内容
        Product cityBike = new Product("city bike", "bk-1", 123.0);

        // 构造请求
        IndexRequest<Object> indexRequest = IndexRequest.of(fn -> fn
                .index(INDEX_NAME)
                .id(cityBike.getSku() + random.nextInt())
                .document(cityBike)
        );

        // 客户端执行请求
        IndexResponse response = client.index(indexRequest);
        System.out.println(response.index());
        System.out.println(response);
    }

    /**
     * 创建文档 - 方式3
     */
    public void testAddDocument03() throws IOException {
        // 文档内容
        Product cityBike = new Product("city bike", "bk-1", 123.0);

        // 客户端执行请求
        IndexResponse response = client.index(fn -> fn
                .index(INDEX_NAME)
                .id(cityBike.getSku() + random.nextInt())
                .document(cityBike));

        System.out.println(response.index());
        System.out.println(response);
    }

    /**
     * 更新文档 (存在则更新，不存在则创建）
     */
    public void testUpdateDocument() throws IOException {
        Product product = new Product("city bike", "bk-1", 200);

        UpdateResponse<Product> update = client.update(updateRequest -> updateRequest
                        .index(INDEX_NAME)
                        .id("bk-1")
                        .doc(product),
//                        .id("bk-1")
//                        .upsert(product),
                Product.class);

        System.out.println(update);
    }

    /**
     * 删除文档
     *
     * @throws IOException
     */
    public void testDeleteDocument() throws IOException {
        DeleteResponse delete = client.delete(d ->
                // 指定索引
                d.index(INDEX_NAME)
                        .id("bk-1"));
        System.out.println(delete);
    }

    /**
     * 获取指定文档 ducument
     */
    public void testGetDocument() throws IOException {
        // 查询指定id
        GetRequest.Builder builder1 = new GetRequest.Builder();
        builder1.index(INDEX_NAME)
                .id("bk-1");

        GetResponse<Product> productGetResponse = client.get(
                builder1.build(), // 构建请求
                Product.class);  // 结果映射
        System.out.println(productGetResponse);

        // 如果没有对应的映射类，可以用普通json类来映射(必须是能被 elastic search 序列化的）
        GetRequest.Builder builder2 = new GetRequest.Builder();
        builder2.index(INDEX_NAME)
                .id("bk-1");

        GetResponse<ObjectNode> rawGetResponse = client.get(
                builder2.build(), // 构建请求
                ObjectNode.class);  // 结果映射
        System.out.println(rawGetResponse);
    }

    /**
     * 搜索文档 （普通方式）
     * 通过构造方法层层构造，实际使用的时候不用去记要哪些。
     * 应该从客户端发搜索请求开始，client.search(searchRequest, Product.class)，查看他所需要的参数，层层往上剥
     *
     * @throws IOException
     */
    public void testSearchDocument() throws IOException {
        String searchText = "bike";
        // 构造 match 的参数
        MatchQuery.Builder matchQueryBuilder = new MatchQuery.Builder();
        matchQueryBuilder
                .field("name") // 匹配字段
                .query(searchText); // 匹配值
        MatchQuery matchQuery = matchQueryBuilder.build();

        // 构造最终查询参数
        Query.Builder queryBuilder = new Query.Builder();
        queryBuilder.match(matchQuery);
        Query query = queryBuilder.build();

        // 构造搜索请求
        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder();
        searchRequestBuilder.index(INDEX_NAME)
                .query(query);
        SearchRequest searchRequest = searchRequestBuilder.build();

        // 文档客户端执行查询
        SearchResponse<Product> response = client.search(searchRequest, Product.class);
        System.out.println(response);
    }

    /**
     * 搜索文档 （lambda模式）
     *
     * @throws IOException
     */
    public void testSearchDocumentByLambda() throws IOException {
        String searchText = "bike";

        SearchResponse<Product> response = client.search(s -> s
                        .index("products")
                        .query(q -> q
                                .match(t -> t
                                        .field("name")
                                        .query(searchText)
                                )
                        ),
                Product.class
        );

        System.out.println(response);
    }


    /**
     * 获取所有文档 （lambda模式）
     *
     * @throws IOException
     */
    public void testSearchAllDocument() throws IOException {
        // 查询所有传个空对象
        MatchAllQuery matchAllQuery = MatchAllQuery.of(matchAllQueryFn -> matchAllQueryFn);
        Query query = Query.of(fn -> fn.matchAll(matchAllQuery));
        SearchRequest searchRequest = SearchRequest.of(fn -> fn
                .index(INDEX_NAME)
                .query(query)
        );

        // 客户端执行查询请求
        SearchResponse<Product> response = client.search(searchRequest, Product.class);
        System.out.println(response);
    }

    /**
     * 多层嵌入式搜索
     * 请求 -》 最终搜索参数 -》 子搜索参数（可多个）
     */
    public void testNestedSearch() throws IOException {
        String searchText = "bike";
        double maxPrice = 100.0;

        // name 搜索 包含
        Query nameQuery = MatchQuery.of(matchQueryFn -> matchQueryFn
                        .field("name")
                        .query(searchText))
                ._toQuery();

        // price 价格比 maxPrice 高
        Query rangeQuery = RangeQuery.of(r -> r
                        .field("price")
                        .gte(JsonData.of(maxPrice)))
                ._toQuery();
        //
        Query query = Query.of(queryFn -> queryFn
                .bool(b -> b
                        .must(nameQuery)
                        .must(rangeQuery)));

        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(INDEX_NAME)
                .query(query));

        SearchResponse<Product> search = client.search(searchRequest, Product.class);
        System.out.println(search);

    }


    // ======================= 通过模板查询 ======================

    String templateId = "query-script";

    // 创建模板
    public void testCreateTemplate() throws IOException {
        StoredScript mustacheTemplate = StoredScript.of(fn -> fn
                .lang("mustache")
                // 模板 json格式， {{}} 占位符
                .source("{\"query\":{\"match\":{\"{{field}}\":\"{{value}}\"}}}"));

        PutScriptRequest putScriptRequest = PutScriptRequest.of(fn -> fn
                .id(templateId)
                .script(mustacheTemplate));

        PutScriptResponse putScriptResponse = client.putScript(putScriptRequest);

        System.out.println("putScriptResponse = " + putScriptResponse);
    }


    /**
     * 搜索指定模板
     *
     * @throws IOException
     */
    public void testGetSearchTemplate() throws IOException {
        GetScriptRequest.Builder builder = new GetScriptRequest.Builder();
        builder.id(templateId);
        GetScriptRequest request = builder.build();

        // 执行搜索模板请求
        GetScriptResponse script = client.getScript(request);
        System.out.println(script);
    }


    /**
     * 使用模板查询
     *
     * @throws IOException
     */
    public void testSearchTemplate() throws IOException {
        SearchTemplateRequest searchTemplateRequest = SearchTemplateRequest.of(fn -> fn
                        .index(INDEX_NAME)
                        .id(templateId)
                        .params("field", JsonData.of("name"))
                        .params("value", JsonData.of("bike")));

        SearchTemplateResponse<Product> searchTemplateResponse = client.searchTemplate(searchTemplateRequest, Product.class);
        System.out.println("searchTemplateResponse = " + searchTemplateResponse);
    }

}
