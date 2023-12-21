package org.example;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.*;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.sun.xml.internal.ws.streaming.TidyXMLStreamReader;
import junit.framework.TestCase;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.example.entity.Product;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 批量操作文档
 * https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/indexing-bulk.html#_indexing_application_objects
 *
 * @author hongchuzhen
 * @date 12/21/2023
 */
public class MultiDocumentTest extends TestCase {
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

    Random random = new Random();

    static String INDEX_NAME = "products";

    /**
     * 连接客户端
     *
     * @return
     * @throws IOException
     */
    public void testGetClient() throws IOException {
        // Create the low-level client
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 5302)).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // And create the block API client
        client = new ElasticsearchClient(transport);
    }


    // =============== bulk: indexing multiple documents ==========
    // 批量操作文档

    /**
     * 批量插入文档
     *
      * @throws IOException
     */
    public void testMultiObjects01() throws IOException {
        // 创造对象列表
        List<Product> list = Arrays.asList(
                new Product("name1", "sku-1" + random.nextInt(), random.nextInt()),
                new Product("name2", "sku-2" + random.nextInt(), random.nextInt()),
                new Product("name3", "sku-3" + random.nextInt(), random.nextInt())
        );

        // 构造 buik 的操作集合
        BulkRequest.Builder bulkRequestBuilder = new BulkRequest.Builder();
        for (Product product : list) {
            // op is is a builder for BulkOperation which is a variant type.
            // This type has index, create, update and delete variants.
            bulkRequestBuilder.operations(op -> op
                    // 构建索引操作索引操作
                    .index(idx -> idx
                            // 设置索引、id、文档内容
                            .index(INDEX_NAME)
                            .id(product.getSku())
                            .document(product)));
        }
        // 构建批量处理请求
        BulkRequest bulkRequest = bulkRequestBuilder.build();

        BulkResponse bulk = client.bulk(bulkRequest);
        System.out.println(bulk);
    }

    /**
     * 批量插入文档
     * 总体流程为
     * 1、 封装简单操作 （索引、文档操作）
     * 2、 把简单操作封装为 bulk操作
     * 3、 用封装的 bulk操作集合 构建批量处理请求
     * 4、 客户端处理批量处理请求
     *
     *
     * @throws IOException
     */
    public void testMultiObjects02() throws IOException {
        // 创造对象列表
        List<Product> list = Arrays.asList(
                new Product("name1", "sku-1" + random.nextInt(), random.nextInt()),
                new Product("name2", "sku-2" + random.nextInt(), random.nextInt()),
                new Product("name3", "sku-3" + random.nextInt(), random.nextInt())
        );

        // 批处理操作集合
        List<BulkOperation> bulkOperations = new ArrayList<>();

        for (Product product : list) {
            // 构建索引操作
            IndexOperation<Object> indexOperation = IndexOperation.of(idx -> idx
                    .index(INDEX_NAME)
                    .id(product.getSku())
                    .document(product));
            // bulk 操作
            BulkOperation bulkOperation = BulkOperation.of(bulkOp -> bulkOp
                    .index(indexOperation));

            // 构建批量处理请求
            bulkOperations.add(bulkOperation);
        }
        // 构造批处理请求
        BulkRequest.Builder bulkRequestBuilder = new BulkRequest.Builder();
        bulkRequestBuilder.operations(bulkOperations);
        BulkRequest bulkRequest = bulkRequestBuilder.build();

        // 客户端执行批处理请求
        BulkResponse bulk = client.bulk(bulkRequest);
        System.out.println(bulk);
    }

    /**
     * 通过 io 的方式批量操作
     * 自己看官方文档。。。
     */
}
