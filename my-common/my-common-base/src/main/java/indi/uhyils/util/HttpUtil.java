package indi.uhyils.util;

import com.alibaba.fastjson.JSONObject;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * http请求类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月26日 10时56分
 */
public final class HttpUtil {

    /**
     * 返回成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    private HttpUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 发送GET请求
     *
     * @param url  请求url
     * @param head 请求参数
     *
     * @return JSON或者字符串
     *
     * @throws Exception
     */
    public static Object sendHttpGet(String url, Map<String, Object> head) throws Exception {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            /**
             * 创建HttpClient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建URIBuilder
             */
            URIBuilder uriBuilder = new URIBuilder(url);
            /**
             * 创建HttpGet
             */
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            /**
             * 设置请求头部编码
             */
            List<BasicHeader> heads = getHeads(head);
            for (BasicHeader nameValuePair : heads) {
                httpGet.setHeader(nameValuePair);
            }
            /**
             * 请求服务
             */
            response = client.execute(httpGet);
            /**
             * 获取响应吗
             */
            int statusCode = response.getStatusLine().getStatusCode();

            if (SUCCESS_CODE == statusCode) {
                /**
                 * 获取返回对象
                 */
                HttpEntity entity = response.getEntity();
                /**
                 * 通过EntityUitls获取返回内容
                 */
                String result = EntityUtils.toString(entity, StandardCharsets.UTF_8);

                /**
                 * 转换成json,根据合法性返回json或者字符串
                 */
                try {
                    jsonObject = JSONObject.parseObject(result);
                    return jsonObject;
                } catch (Exception e) {
                    return result;
                }
            } else {
                LogUtil.error(HttpUtil.class, "GET请求失败");
            }
        } catch (Exception e) {
            LogUtil.error(HttpUtil.class, "HttpClientService-line: " + 100 + ", Exception: " + e);
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        return null;
    }


    /**
     * 发送GET请求
     *
     * @param url  请求url
     * @param head 请求参数
     *
     * @return JSON或者字符串
     *
     * @throws Exception
     */
    public static Object sendHttpsGet(String url, Map<String, Object> head) throws Exception {
        JSONObject jsonObject = null;
        CloseableHttpResponse response = null;
        try (
            /**
             * 创建HttpClient对象
             */
            CloseableHttpClient client = new SSLClient()
        ) {
            /**
             * 创建URIBuilder
             */
            URIBuilder uriBuilder = new URIBuilder(url);
            /**
             * 创建HttpGet
             */
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            /**
             * 设置请求头部编码
             */
            List<BasicHeader> heads = getHeads(head);
            for (BasicHeader nameValuePair : heads) {
                httpGet.setHeader(nameValuePair);
            }
            /**
             * 请求服务
             */
            response = client.execute(httpGet);
            /**
             * 获取响应吗
             */
            int statusCode = response.getStatusLine().getStatusCode();

            if (SUCCESS_CODE == statusCode) {
                /**
                 * 获取返回对象
                 */
                HttpEntity entity = response.getEntity();
                /**
                 * 通过EntityUitls获取返回内容
                 */
                String result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                /**
                 * 转换成json,根据合法性返回json或者字符串
                 */
                try {
                    jsonObject = JSONObject.parseObject(result);
                    return jsonObject;
                } catch (Exception e) {
                    return result;
                }
            } else {
                LogUtil.error(HttpUtil.class, "GET请求失败");
            }
        } catch (Exception e) {
            LogUtil.error(HttpUtil.class, "HttpClientService-line: " + 100 + ", Exception: " + e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     * 发送POST请求
     *
     * @param url    url
     * @param heads  请求头
     * @param params 参数
     *
     * @return JSON或者字符串
     *
     * @throws Exception
     */
    public static Object sendHttpPost(String url, Map<String, Object> heads, Map<String, Object> params) throws Exception {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            /**
             *  创建一个httpclient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 包装成一个Entity对象
             */
            StringEntity entity = new UrlEncodedFormEntity(getParams(params), StandardCharsets.UTF_8);
            /**
             * 设置请求的内容
             */
            post.setEntity(entity);
            /**
             * 设置请求头部编码
             */
            List<BasicHeader> head = getHeads(heads);
            for (BasicHeader nameValuePair : head) {
                post.setHeader(nameValuePair);
            }
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                /**
                 * 通过EntityUitls获取返回内容
                 */
                String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                /**
                 * 转换成json,根据合法性返回json或者字符串
                 */
                try {
                    jsonObject = JSONObject.parseObject(result);
                    return jsonObject;
                } catch (Exception e) {
                    return result;
                }
            } else {
                LogUtil.error(HttpUtil.class, "请求失败");
            }
        } catch (Exception e) {
            LogUtil.error(HttpUtil.class, e);
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        return null;
    }


    /**
     * 发送POST请求
     *
     * @param url    url
     * @param heads  请求头
     * @param params 参数
     *
     * @return JSON或者字符串
     *
     * @throws Exception
     */
    public static Object sendHttpsPost(String url, Map<String, Object> heads, Map<String, Object> params) throws Exception {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            /**
             *  创建一个httpclient对象
             */
            client = new SSLClient();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 包装成一个Entity对象
             */
            StringEntity entity = new UrlEncodedFormEntity(getParams(params), "UTF-8");
            /**
             * 设置请求的内容
             */
            post.setEntity(entity);
            /**
             * 设置请求头部编码
             */
            List<BasicHeader> head = getHeads(heads);
            for (BasicHeader nameValuePair : head) {
                post.setHeader(nameValuePair);
            }
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                /**
                 * 通过EntityUitls获取返回内容
                 */
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                /**
                 * 转换成json,根据合法性返回json或者字符串
                 */
                jsonObject = JSONObject.parseObject(result);
                return jsonObject;

            } else {
                LogUtil.error(HttpUtil.class, "请求失败");
            }
        } catch (Exception e) {
            LogUtil.error(HttpUtil.class, e);
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        return null;
    }

    /**
     * 组织请求参数{参数名和参数值下标保持一致}
     *
     * @param params 参数数组
     *
     * @return 参数对象
     */
    private static List<NameValuePair> getParams(Map<String, Object> params) {
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        return nameValuePairList;
    }

    /**
     * 组织请求参数{参数名和参数值下标保持一致}
     *
     * @param heads 参数数组
     *
     * @return 参数对象
     */
    private static List<BasicHeader> getHeads(Map<String, Object> heads) {
        List<BasicHeader> nameValuePairList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : heads.entrySet()) {
            nameValuePairList.add(new BasicHeader(entry.getKey(), entry.getValue().toString()));
        }
        return nameValuePairList;
    }
}
