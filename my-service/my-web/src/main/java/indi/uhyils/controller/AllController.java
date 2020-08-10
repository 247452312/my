package indi.uhyils.controller;

import com.alibaba.fastjson.JSON;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.request.Action;
import indi.uhyils.pojo.request.SessionRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.pojo.response.WebResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.util.DubboApiUtil;
import indi.uhyils.util.LogPushUtils;
import indi.uhyils.util.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月24日 16时14分
 */
@Controller
@CrossOrigin
public class AllController {


    private static final String TOKEN = "token";

    /**
     * 默认返回所有的方法
     *
     * @param action             包含请求参数的信息
     * @param httpServletRequest 请求,暂时没用,日后或许有用
     * @return 向界面返回的值
     */
    @RequestMapping("action")
    @ResponseBody
    public WebResponse action(@RequestBody Action action, HttpServletRequest httpServletRequest) {
        String eMsg = null;
        LinkNode<String> link = null;
        ServiceResult serviceResult = null;

        LogUtil.info(this, "param: " + JSON.toJSONString(action));
        // token修改到arg中
        action.getArgs().put(TOKEN, action.getToken());
        // 添加链路跟踪
        actionAddRequestLink(action);
        try {
            List list = new ArrayList();
            list.add(action.getArgs());
            serviceResult = DubboApiUtil.dubboApiTool(action.getInterfaceName(), action.getMethodName(), list, new DefaultRequest());
            link = serviceResult.getRequestLink();
            LogUtil.linkPrint(link);
            if (!serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
                eMsg = serviceResult.getServiceMessage();
            }
            return WebResponse.build(serviceResult);
        } catch (Exception e) {
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

    @PostMapping("/getSession")
    @ResponseBody
    public Object getSession(@RequestBody SessionRequest sessionRequest, HttpServletRequest request) {
        LogUtil.info(this, "getSession: " + sessionRequest.getAttrName());
        HttpSession session = request.getSession();
        LogUtil.info(this, "result: " + session.getAttribute(sessionRequest.getAttrName()));
        return session.getAttribute(sessionRequest.getAttrName());
    }

    @PostMapping("/setSession")
    @ResponseBody
    public boolean setSession(@RequestBody SessionRequest sessionRequest, HttpServletRequest request) {
        LogUtil.info(this, "setSession: " + sessionRequest.getAttrName());
        LogUtil.info(this, "sessionData : " + sessionRequest.getData());
        HttpSession session = request.getSession();
        session.setAttribute(sessionRequest.getAttrName(), sessionRequest.getData());
        return true;
    }


    /**
     * action 添加链路跟踪起点
     *
     * @param action
     */
    public static void actionAddRequestLink(@RequestBody Action action) {
        HashMap<String, Object> requestLink = new HashMap<>(2);
        requestLink.put("class", "indi.uhyils.pojo.request.model.LinkNode");
        requestLink.put("data", "页面请求");
        action.getArgs().put("requestLink", requestLink);
    }

}
