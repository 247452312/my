package indi.uhyils.protocol.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.util.LogUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 08时44分
 */
@RestController
@CrossOrigin
public class GatewayController {

    /**
     * 外界http调用
     *
     * @param httpServletRequest 请求,暂时没用,日后或许有用
     *
     * @return 向界面返回的值
     */
    @RequestMapping("invoke")
    public Object postInvoke(HttpServletRequest httpServletRequest) throws IOException {
        // 方法
        final String method = httpServletRequest.getMethod();
        // 请求头
        Map<String, String> headerParam = getHeaderParam(httpServletRequest);
        // get参数
        final Map<String, String[]> getParams = httpServletRequest.getParameterMap();
        // post参数
        Map<String, Object> postParams = getPostParam(httpServletRequest);
        LogUtil.info("对接中心http请求方法:{}", method);
        LogUtil.info("header:{}", JSON.toJSONString(headerParam));
        LogUtil.info("get参数:{}", JSON.toJSONString(getParams));
        LogUtil.info("post参数:{}", JSON.toJSONString(postParams));
        return null;
    }

    /**
     * 获取请求头
     *
     * @param httpServletRequest
     *
     * @return
     */
    private Map<String, String> getHeaderParam(HttpServletRequest httpServletRequest) {
        final Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        Map<String, String> result = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            final String s = headerNames.nextElement();
            final String header = httpServletRequest.getHeader(s);
            result.put(s, header);
        }
        return result;
    }

    /**
     * 获取post参数
     *
     * @param httpServletRequest
     *
     * @return
     */
    private Map<String, Object> getPostParam(HttpServletRequest httpServletRequest) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
        String str = "";

        StringBuilder sb = new StringBuilder();
        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }
        final String s = sb.toString();
        return JSONObject.parseObject(s);

    }


}
