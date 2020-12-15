package indi.uhyils.es.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.es.dao.EsDao;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月11日 14时00分
 */
@Component
public class EsDaoImpl implements EsDao {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public Boolean createIndex(String index) throws IOException {
        //创建索引对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("xc_course");
        //设置参数
        createIndexRequest.settings(Settings.builder().put("number_of_shards", "1").put("number_of_replicas", "0"));
        //操作索引的客户端
        IndicesClient indices = client.indices();
        //执行创建索引库
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest, RequestOptions.DEFAULT);
        //得到响应
        return createIndexResponse.isAcknowledged();
    }

    @Override
    public Boolean insert(String index, String type, String id, String source) throws IOException {
        IndexRequest request = new IndexRequest(index, type, id);
        request.source(source, XContentType.JSON);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        return indexResponse.status() == RestStatus.OK;
    }


    @Override
    public Boolean insert(String index, String type, String id, Object obj) throws IOException {
        IndexRequest request = new IndexRequest(index, type, id);
        request.source(JSONObject.toJSONString(obj));
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        return indexResponse.status() == RestStatus.OK;
    }

    @Override
    public Boolean insert(String index, String type, String id, byte[] bytes) throws IOException {
        IndexRequest request = new IndexRequest(index, type, id);
        request.source(bytes);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        return indexResponse.status() == RestStatus.OK;
    }

    @Override
    public Boolean insert(String index, String type, String id, HashMap<String, Object> map) throws IOException {
        IndexRequest request = new IndexRequest(index, type, id);
        request.source(map);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        return indexResponse.status() == RestStatus.OK;
    }

    @Override
    public Boolean delete(String index, String type, String id) throws IOException {
        //删除索引请求对象
        DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
        //响应对象
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        //获取响应结果
        return deleteResponse.getResult() == DocWriteResponse.Result.DELETED;
    }

    @Override
    public Map<String, Object> getMapById(String index, String type, String id) throws IOException {
        //查询请求对象
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        //得到文档的内容
        return getResponse.getSourceAsMap();
    }

    @Override
    public <T> T getObjById(String index, String type, String id, Class<T> clazz) throws IOException {
        //查询请求对象
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        //得到文档的内容
        String json = getResponse.getSourceAsString();
        return JSON.parseObject(json, clazz);
    }

    @Override
    public String getById(String index, String type, String id) throws IOException {
        //查询请求对象
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        //得到文档的内容
        return getResponse.getSourceAsString();
    }

    @Override
    public byte[] getByteById(String index, String type, String id) throws IOException {
        //查询请求对象
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        //得到文档的内容
        return getResponse.getSourceAsBytes();
    }
}
