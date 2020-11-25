package com.wxss.springbootesdemo.service.impl;

import com.wxss.springbootesdemo.service.IEsService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: wxss
 * @Date: 2020/11/25
 * @Description:
 */
@Service
public class EsServiceImpl implements IEsService {

    @Autowired
    @Qualifier("myRestHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    private static final RequestOptions DEFAULT_REQUEST_OPTIONS = RequestOptions.DEFAULT;

    @Override
    public boolean createIndex(String indexName, String mappingJsonStr) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        // 设置索引的分片和副本数量
        createIndexRequest.settings(Settings.builder()
                .put("number_of_shards",1)
                .put("number_of_replicas",0));
        createIndexRequest.mapping(mappingJsonStr, XContentType.JSON);
        // 创建索引操作客户端
        IndicesClient indices = restHighLevelClient.indices();
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest, RequestOptions.DEFAULT);

        return createIndexResponse.isAcknowledged();
    }

    @Override
    public boolean deleteIndex(String indexName) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        IndicesClient indices = restHighLevelClient.indices();
        AcknowledgedResponse deleteResponse = indices.delete(deleteIndexRequest, DEFAULT_REQUEST_OPTIONS);

        return deleteResponse.isAcknowledged();
    }
}
