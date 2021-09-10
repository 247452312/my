package indi.uhyils.util;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.CodeEnum;
import indi.uhyils.pojo.DO.ApiDO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.entity.Api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * 负责api的发送等请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月28日 07时17分
 */
public class ApiUtils {


    /**
     * 调用api群
     *
     * @param apis 已经排好序的api们
     *
     * @return 结果
     */
    public static String callApi(List<Api> apis, UserDTO userEntity, Map<String, String> parameter) {
        // 初始化调用群期间可传递的参数
        parameter.put("${username}", userEntity.getUsername());
        parameter.put("${nickName}", userEntity.getNickName());
        parameter.put("${mail}", userEntity.getMail());
        parameter.put("${phone}", userEntity.getPhone());
        String result = null;
        int apiIndex = 0;
        // 遍历api群中的每一个api(已经排好序)
        for (Api api : apis) {
            ApiDO apiDO = api.toDo();
            String head = apiDO.getHead();
            String param = apiDO.getParam();
            // 初始化head
            HashMap<String, String> httpHead = new HashMap<>(16);
            if (!StringUtils.isEmpty(head)) {
                // 注入head中的参数
                head = replaceString(parameter, head);
                // 解析请求头
                String[] split = head.split("\n");
                for (String s : split) {
                    String[] split1 = s.split(":");
                    assert split1.length == 2;
                    split1[0] = replaceString(parameter, split1[0]);
                    split1[1] = replaceString(parameter, split1[1]);
                    httpHead.put(split1[0], split1[1]);
                }
            }
            // 初始化参数 (post用)
            byte[] paramByte = null;
            if (!StringUtils.isEmpty(param)) {
                // 注入param中的参数
                for (Map.Entry<String, String> en : parameter.entrySet()) {
                    param = param.replaceAll(en.getKey(), en.getValue());
                }
                // 获取流
                paramByte = param.getBytes(StandardCharsets.UTF_8);
            }
            result = getHttpResultString(parameter, result, apiIndex, apiDO, param, httpHead, paramByte);
            apiIndex++;
        }
        LogUtil.info(ApiUtils.class, "api调用群结束");
        return result;
    }

    public static String replaceString(Map<String, String> parameter, String head) {
        for (Map.Entry<String, String> en : parameter.entrySet()) {
            String key = en.getKey();
            String value = en.getValue();
            if (head.contains(key)) {
                int index = head.indexOf(key);
                head = head.substring(0, index) + value + head.substring(index + key.length());
            }
        }
        return head;
    }

    /**
     * 获取http请求的结果
     *
     * @param map       之前请求中的结果
     * @param result    api群-最终结果
     * @param apiIndex  api在群中的index
     * @param api       正在进行的api
     * @param param     参数
     * @param httpHead  解析完成的请求头
     * @param paramByte 参数byte
     *
     * @return 此次http结果
     */
    private static String getHttpResultString(Map<String, String> map, String result, int apiIndex, ApiDO api, String param, HashMap<String, String> httpHead, byte[] paramByte) {
        HttpURLConnection con = null;
        BufferedReader read = null;
        OutputStream outputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL urlC = new URL(api.getUrl());
            //得到连接对象
            con = (HttpURLConnection) urlC.openConnection();
            //设置请求类型
            con.setRequestMethod(api.getType());
            setHttpHead(api, httpHead, con);

            //允许写出
            con.setDoOutput(Boolean.TRUE);
            //允许读入
            con.setDoInput(Boolean.TRUE);
            //不使用缓存
            con.setUseCaches(false);
            if (paramByte != null && paramByte.length != 0) {
                LogUtil.info(ApiUtils.class, "请求参数: " + param);
                outputStream = con.getOutputStream();
                outputStream.write(paramByte);
            }
            //得到响应码
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //得到响应流
                InputStream inputStream = con.getInputStream();
                //将响应流转换成字符串
                String line;
                read = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                while ((line = read.readLine()) != null) {
                    sb.append(line);
                }
                String text = sb.toString();
                switch (Objects.requireNonNull(CodeEnum.prase(api.getResultCode()))) {
                    case UTF_8:
                        text = StringEncodingUtils.decodeUTF8String(text);
                        break;
                    case UNICODE:
                        text = StringEncodingUtils.decodeUnicodeString(text);
                        break;
                    default:
                        break;
                }
                LogUtil.info(ApiUtils.class, "请求返回值: " + text);
                result = text;
                JSONObject jsonObject = JSONObject.parseObject(text);
                for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    map.put("${" + apiIndex + "." + entry.getKey() + "}", entry.getValue().toString());
                }
            } else {
                // 调用错误时抛出异常
                String line;
                read = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                while ((line = read.readLine()) != null) {
                    sb.append(line);
                }
                read.close();
                if (outputStream != null) {
                    outputStream.close();
                }
                throw new Exception(sb.toString());
            }
        } catch (Exception e) {
            LogUtil.error(ApiUtils.class, e);
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    LogUtil.error(ApiUtils.class, e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LogUtil.error(ApiUtils.class, e);
                }
            }
        }
        return result;
    }

    private static void setHttpHead(ApiDO api, HashMap<String, String> httpHead, HttpURLConnection con) {
        // 设置请求头
        LogUtil.info(ApiUtils.class, "发送http请求: " + api.getUrl());
        LogUtil.info(ApiUtils.class, "请求头开始: ");
        for (Map.Entry<String, String> stringStringEntry : httpHead.entrySet()) {
            con.setRequestProperty(stringStringEntry.getKey(), stringStringEntry.getValue());
            LogUtil.info(ApiUtils.class, "\t" + stringStringEntry.getKey() + ":" + stringStringEntry.getValue());
        }
        LogUtil.info(ApiUtils.class, "请求头结束");
    }
}
