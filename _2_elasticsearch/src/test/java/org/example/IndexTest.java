package org.example;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import junit.framework.TestCase;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.Map;

/**
 * 每个 test 方法的方法名需要以 test 开头
 * <p>
 * Unit test for simple App.
 */
public class IndexTest extends TestCase {


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public IndexTest(String testName) {
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

    /**
     * 查看索引
     *
     * @throws IOException
     */
    public void testGetIndex() throws IOException {

        // 查看指定索引
        GetIndexResponse getIndexResponse = client.indices().get(c -> c.index(INDEX_NAME));
        Map<String, IndexState> result = getIndexResponse.result();
        result.forEach((k, v) -> System.out.format("key=%s,value=%s\n", k, v));

        System.out.println("-----------------------");

        // 查看所有索引
        ElasticsearchIndicesClient indexClient = client.indices();
        GetIndexResponse allResponse = indexClient.get(c -> c.index("*"));
        System.out.println(allResponse);

    }

    /**
     * 删除索引
     */
    public void testDeleteIndex() throws IOException {
        DeleteIndexResponse delete = client.indices().delete(c -> c.index(INDEX_NAME));
        System.out.println(delete);
    }


}
