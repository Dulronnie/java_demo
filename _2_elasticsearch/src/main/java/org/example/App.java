package org.example;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexState;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.awt.image.Kernel;
import java.io.IOException;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        ElasticsearchClient client = getClient();

//        createIndex();
        getIndex();
    }

    /**
     * 连接客户端
     * @return
     * @throws IOException
     */
    public static ElasticsearchClient getClient() throws IOException {
        // Create the low-level client
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 5302)).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);

        return client;
    }

    /**
     * 创建索引
     * @throws IOException
     */
    public static void createIndex() throws IOException {
        ElasticsearchClient client = getClient();

        CreateIndexResponse response = client.indices().create(c -> c.index("products"));
        boolean acknowledged = response.acknowledged();
        boolean shardsAcknowledged = response.shardsAcknowledged();
        String index = response.index();

        System.out.format("创建索引状态%s",acknowledged);
        System.out.format("已确认的分片%s",shardsAcknowledged);
        System.out.format("索引名称%s",index);
    }

    /**
     * 查看索引
     * @throws IOException
     */
    public static void getIndex() throws IOException {
        ElasticsearchClient client = getClient();

        // 查看指定索引
        GetIndexResponse getIndexResponse = client.indices().get(c -> c.index("createindex"));
        Map<String, IndexState> result = getIndexResponse.result();
        result.forEach((k,v) -> System.out.format("key=%s,value=%s",k,v));


        // 查看所有索引

    }
}
