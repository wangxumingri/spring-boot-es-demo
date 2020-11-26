package com.wxss.springbootesdemo.service.impl;

import com.wxss.springbootesdemo.service.IEsService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
                .put("number_of_shards", 1)
                .put("number_of_replicas", 0));
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


    @Override
    public String addDocument(String index, String type,Map<String, Object> docContent) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index);
        // 指定文档内容
        indexRequest.source(docContent);
        IndexResponse addDocResponse = restHighLevelClient.index(indexRequest, DEFAULT_REQUEST_OPTIONS);
        System.out.println(addDocResponse.getId());
        return addDocResponse.getId();
    }
    @Override
    public Map<String,Object> getDocument(String index,String type,String docId) throws IOException {
        GetRequest getRequest = new GetRequest(index, docId);
        GetResponse  getDocumentRes= restHighLevelClient.get(getRequest, DEFAULT_REQUEST_OPTIONS);

        Map<String, Object> documentContent = getDocumentRes.getSourceAsMap();

        return documentContent;
    }


    @Override
    public boolean updateDocument(String index, String docId, Map<String, Object> content) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(index, docId);
        updateRequest.doc(content);
        UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update);
        return true;
    }

    @Override
    public boolean deleteDocument(String index, String docId) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index, docId);
        DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete.status());
        return true;
    }
}
