package org.example;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import junit.framework.TestCase;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

/**
 * @author hongchuzhen
 * @date 12/21/2023
 */
public class ClientTest extends TestCase {
    /**
     * 每个测试方法执行前执行
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        System.out.println("---------------- start test ---------------");

    }

    /**
     * 每个测试方法执行后执行
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.out.println("------------------- end test -----------------");
    }

    public static String INDEX_NAME = "products";

    /**
     * 创建 阻塞式 客户端
     * @return
     * @throws IOException
     */
    public void testSyncClient() throws IOException {
        // Create the low-level client
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 5302)).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the block API client
        ElasticsearchClient client = new ElasticsearchClient(transport);

        // 是否存在
        if (client.exists(b -> b.index(INDEX_NAME).id("foo")).value()){
            System.out.println("product exists");
        }else {
            System.out.println("product not exists");
        }
    }


    /**
     * 创建 非阻塞式 客户端
     * @return
     * @throws IOException
     */
    public void testAsyncClient() throws IOException, InterruptedException {
        // Create the low-level client
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 5302)).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // Asynchronous non-blocking client
        ElasticsearchAsyncClient asyncClient = new ElasticsearchAsyncClient(transport);

        asyncClient.exists(b -> b.index("products").id("foo"))
                // 回调方法
                .whenComplete((response,exception) -> {
                    if (exception != null) {
                        System.out.println("failed to index :");
                        exception.printStackTrace();
                    } else {
                        System.out.println("product exists: " + response.value());
                    }
                });

        // 睡一觉，等等回调函数
        Thread.sleep(2000);
    }


}
