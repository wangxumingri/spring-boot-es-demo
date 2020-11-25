package com.wxss.springbootesdemo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wxss
 * @Date: 2020/11/24
 * @Description:
 */
@Configuration
public class ElasticsearchConfig {

    private static final String SCHEMA = "http";

    @Value("${wxss.elasticsearch.hostlist}")
    private String hostList;


    /**
     * 创建客户端
     *
     * @return
     */
    @Bean("myRestHighLevelClient")
    public RestHighLevelClient restHighLevelClient() {
        String[] hostArray = hostList.split(",");
        HttpHost[] httpHosts = new HttpHost[hostArray.length];

        for (int i = 0; i < httpHosts.length; i++) {
            String hostAndPort = hostArray[i];
            httpHosts[i] = new HttpHost(hostAndPort.split(":")[0], Integer.parseInt(hostAndPort.split(":")[1]), SCHEMA);
        }
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }

    //项目主要使用RestHighLevelClient，对于低级的客户端暂时不用
    @Bean
    public RestClient restClient() {
        String[] hostArray = hostList.split(",");
        HttpHost[] httpHosts = new HttpHost[hostArray.length];

        for (int i = 0; i < httpHosts.length; i++) {
            String hostAndPort = hostArray[i];
            httpHosts[i] = new HttpHost(hostAndPort.split(":")[0], Integer.parseInt(hostAndPort.split(":")[1]), SCHEMA);
        }

        return RestClient.builder(httpHosts).build();
    }
}

