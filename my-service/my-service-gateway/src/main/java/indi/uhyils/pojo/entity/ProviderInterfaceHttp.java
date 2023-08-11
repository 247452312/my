package indi.uhyils.pojo.entity;


import static indi.uhyils.mysql.enums.FieldTypeEnum.FIELD_TYPE_FLOAT;
import static indi.uhyils.mysql.enums.FieldTypeEnum.FIELD_TYPE_TINY;
import static indi.uhyils.mysql.enums.FieldTypeEnum.FIELD_TYPE_VARCHAR;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.annotation.Default;
import indi.uhyils.annotation.Nullable;
import indi.uhyils.enums.HttpTypeEnum;
import indi.uhyils.mysql.pojo.DTO.FieldInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.DO.ProviderInterfaceHttpDO;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.HttpUtil;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * http接口子表(ProviderInterfaceHttp)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class ProviderInterfaceHttp extends AbstractProviderExample<ProviderInterfaceHttpDO> implements ProviderExample {

    @Default
    public ProviderInterfaceHttp(ProviderInterfaceHttpDO data) {
        super(data);
    }

    public ProviderInterfaceHttp(Long id) {
        super(id, new ProviderInterfaceHttpDO());
    }

    @Override
    public NodeInvokeResult invoke(Map<String, String> header, Map<String, Object> params, List<ProviderInterfaceParam> shouldParams) {
        ProviderInterfaceHttpDO providerInterfaceHttpDO = toDataAndValidate();
        Integer httpType = providerInterfaceHttpDO.getHttpType();
        HttpTypeEnum byCode = HttpTypeEnum.getByCode(httpType);
        Object httpResult = null;
        try {
            switch (byCode) {
                case GET:
                    String url = makeGetUrl(providerInterfaceHttpDO.getUrl(), params);
                    httpResult = HttpUtil.sendHttpGet(url, header);
                    break;
                case POST:
                    httpResult = HttpUtil.sendHttpPost(providerInterfaceHttpDO.getUrl(), header, params);
                    break;
                default:
                    Asserts.throwException("http调用方式:{} 还未开放,敬请期待", byCode.getName());
            }
        } catch (Exception e) {
            LogUtil.error(this, e);
            Asserts.throwException(e);
        }
        return parseHttpResponse(httpResult);
    }

    /**
     * 解析http返回值
     *
     * @param httpResult
     *
     * @return
     */
    @Nullable
    private NodeInvokeResult parseHttpResponse(Object httpResult) {
        if (httpResult == null) {
            NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);
            nodeInvokeResult.setResult(new ArrayList<>());
            List<FieldInfo> fieldInfos = new ArrayList<>();
            FieldInfo fieldInfo = new FieldInfo(providerInterface.databaseName(), providerInterface.tableName(), providerInterface.tableName(), "result", "result", 0, 0, FIELD_TYPE_FLOAT, (short) 0, (byte) 0);
            fieldInfos.add(fieldInfo);
            nodeInvokeResult.setFieldInfos(fieldInfos);
            return nodeInvokeResult;
        }
        if (httpResult instanceof String) {
            NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);
            List<Map<String, Object>> invokeResult = new ArrayList<>();
            HashMap<String, Object> e = new HashMap<>();
            e.put("result", httpResult);
            invokeResult.add(e);
            nodeInvokeResult.setResult(invokeResult);
            List<FieldInfo> fieldInfos = new ArrayList<>();
            FieldInfo fieldInfo = new FieldInfo(providerInterface.databaseName(), providerInterface.tableName(), providerInterface.tableName(), "result", "result", 0, 0, FIELD_TYPE_VARCHAR, (short) 0, (byte) 0);
            fieldInfos.add(fieldInfo);
            nodeInvokeResult.setFieldInfos(fieldInfos);
            return nodeInvokeResult;
        }
        if (httpResult instanceof JSONObject) {
            JSONObject jsonResult = (JSONObject) httpResult;
            NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(null);
            List<Map<String, Object>> invokeResult = new ArrayList<>();
            invokeResult.add(jsonResult);
            nodeInvokeResult.setResult(invokeResult);
            List<FieldInfo> fieldInfos = new ArrayList<>();
            for (Entry<String, Object> entry : jsonResult.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof Number) {
                    fieldInfos.add(new FieldInfo(providerInterface.databaseName(), providerInterface.tableName(), providerInterface.tableName(), fieldName, fieldName, 0, 0, FIELD_TYPE_FLOAT, (short) 0, (byte) 0));
                } else if (value instanceof Boolean) {
                    fieldInfos.add(new FieldInfo(providerInterface.databaseName(), providerInterface.tableName(), providerInterface.tableName(), fieldName, fieldName, 0, 0, FIELD_TYPE_TINY, (short) 0, (byte) 0));
                } else {
                    fieldInfos.add(new FieldInfo(providerInterface.databaseName(), providerInterface.tableName(), providerInterface.tableName(), fieldName, fieldName, 0, 0, FIELD_TYPE_VARCHAR, (short) 0, (byte) 0));
                }
            }
            nodeInvokeResult.setFieldInfos(fieldInfos);
            return nodeInvokeResult;
        }
        if (httpResult instanceof JSONArray) {
            Asserts.throwException("不支持http返回值为数组的情况");
            return null;
        }

        return null;
    }

    /**
     * 构建url
     *
     * @param url
     * @param params
     *
     * @return
     */
    private String makeGetUrl(String url, Map<String, Object> params) {
        List<String> paramsStrList = new ArrayList<>();
        for (Entry<String, Object> entry : params.entrySet()) {
            String paramStr = entry.getKey() + "=" + entry.getValue();
            paramsStrList.add(paramStr);
        }
        String collect = String.join("&", paramsStrList);
        return url + "?" + collect;
    }
}
