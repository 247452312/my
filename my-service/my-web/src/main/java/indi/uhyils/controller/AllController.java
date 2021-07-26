package indi.uhyils.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.request.Action;
import indi.uhyils.pojo.request.SessionRequest;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.pojo.response.WebResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.redis.hotspot.HotSpotRedisPool;
import indi.uhyils.util.RpcApiUtil;
import indi.uhyils.util.LogPushUtils;
import indi.uhyils.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月24日 16时14分
 */
@RestController
@CrossOrigin
public class AllController {

    /**
     * 用户登录时携带的token的名称
     */
    private static final String TOKEN = "token";
    /**
     * 保证请求幂等性, 不会在前一个相同幂等id执行结束前执行方法
     */
    private static final String UNIQUE = "unique";
    /**
     * 热点缓存
     */
    @Autowired
    private HotSpotRedisPool redisPool;


    /**
     * 默认返回所有的方法
     *
     * @param action             包含请求参数的信息
     * @param httpServletRequest 请求,暂时没用,日后或许有用
     * @return 向界面返回的值
     */
    @PostMapping("action")
    public WebResponse action(@RequestBody Action action, HttpServletRequest httpServletRequest) {
        //错误日志
        String eMsg = null;
        // 链路跟踪
        LinkNode<String> link = null;
        // 服务返回信息
        ServiceResult serviceResult = null;

        // 发送前处理
        dealActionBeforeCall(action);
        try {
            JSONObject o = (JSONObject) RpcApiUtil.rpcApiTool(action.getInterfaceName(), action.getMethodName(), action.getArgs());
            serviceResult = JSON.toJavaObject(o, ServiceResult.class);

            if (!serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
                eMsg = serviceResult.getServiceMessage();
            }
            // 修改字段类型为String,因为前端大数会失去精度
            Serializable data = serviceResult.getData();
            if (data instanceof JSONObject) {
                changeFieldTypeToString((JSONObject) data);
            } else if (data instanceof JSONArray) {
                changeFieldTypeToString((JSONArray) data);
            }
            return WebResponse.build(serviceResult);
        } catch (Exception e) {
            // 如果失败,就返回微服务传回来的错误信息与提示
            LogUtil.error(this, e);
            eMsg = e.getMessage();
            return WebResponse.build(null, ServiceCode.ERROR.getMsg(), ServiceCode.ERROR.getText());
        } finally {
            if (serviceResult != null) {
                try {
                    LogPushUtils.pushLog(eMsg, action.getInterfaceName(), action.getMethodName(), action.getArgs(), link, httpServletRequest, action.getToken(), serviceResult.getServiceCode());
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }

            }
        }
    }


    private void changeFieldTypeToString(JSONObject data) {
        // 因前端大数会失去精度, 所以要转变类型,全部转换为String类型的
        if (data != null) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String s = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof JSONObject) {
                    changeFieldTypeToString((JSONObject) value);
                } else if (value instanceof JSONArray) {
                    changeFieldTypeToString((JSONArray) value);
                } else if (value instanceof Long) {
                    value = value.toString();
                }
                data.put(s, value);
            }
        }
    }

    private void changeFieldTypeToString(JSONArray data) {
        // 因前端大数会失去精度, 所以要转变类型,全部转换为String类型的
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                Object o = data.get(i);
                if (o instanceof JSONArray) {
                    changeFieldTypeToString((JSONArray) o);
                } else if (o instanceof JSONObject) {
                    changeFieldTypeToString((JSONObject) o);
                } else if (o instanceof Long) {
                    o = o.toString();
                }
                data.set(i, o);
            }
        }
    }

    private void dealActionBeforeCall(Action action) {
        LogUtil.info(this, "param: " + JSON.toJSONString(action));
        // token修改到arg中
        action.getArgs().put(TOKEN, action.getToken());
    }

    @PostMapping("/getSession")
    public Object getSession(@RequestBody SessionRequest sessionRequest, HttpServletRequest request) {
        LogUtil.info(this, "getSession: " + sessionRequest.getAttrName());
        HttpSession session = request.getSession();
        LogUtil.info(this, "result: " + session.getAttribute(sessionRequest.getAttrName()));
        return session.getAttribute(sessionRequest.getAttrName());
    }

    @PostMapping("/setSession")
    public boolean setSession(@RequestBody SessionRequest sessionRequest, HttpServletRequest request) {
        LogUtil.info(this, "setSession: " + sessionRequest.getAttrName());
        LogUtil.info(this, "sessionData : " + sessionRequest.getData());
        HttpSession session = request.getSession();
        session.setAttribute(sessionRequest.getAttrName(), sessionRequest.getData());
        return Boolean.TRUE;
    }

}
