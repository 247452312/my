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
import indi.uhyils.rpc.spring.util.RpcApiUtil;
import indi.uhyils.util.LogPushUtils;
import indi.uhyils.util.LogUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;

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
     * action 添加链路跟踪起点
     *
     * @param action
     */
    public static void actionAddRequestLink(Action action) {
        HashMap<String, Object> requestLink = new HashMap<>(2);
        requestLink.put("class", "indi.uhyils.pojo.request.model.LinkNode");
        requestLink.put("data", "页面请求");
        action.getArgs().put("requestLink", requestLink);
    }

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
            serviceResult = RpcApiUtil.rpcApiTool(action.getInterfaceName(), action.getMethodName(), action.getArgs());

            /* 打印链路跟踪 */
            link = serviceResult.getRequestLink();
            LogUtil.linkPrint(link);

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
            for (String s : data.keySet()) {
                Object value = data.get(s);
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
        // 添加链路跟踪
        actionAddRequestLink(action);
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
        return true;
    }

}
