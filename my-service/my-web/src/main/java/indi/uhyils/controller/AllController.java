package indi.uhyils.controller;

import indi.uhyils.enum_.ResponseCode;
import indi.uhyils.pojo.request.Action;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.SessionRequest;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.pojo.response.WebResponse;
import indi.uhyils.util.DubboApiUtil;
import indi.uhyils.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class AllController {

    private static final Logger logger = LoggerFactory.getLogger(AllController.class);


    /**
     * 默认返回所有的方法
     *
     * @param action  包含请求参数的信息
     * @param request
     * @return
     */
    @RequestMapping("action")
    @ResponseBody
    public WebResponse action(@RequestBody Action action, HttpServletRequest request) {

        action.getArgs().put("token", action.getToken());
        actionAddRequestLink(action);
        try {
            List list = new ArrayList();
            list.add(action.getArgs());
            ServiceResult serviceResult = DubboApiUtil.dubboApiTool(action.getInterfaceName(), action.getMethodName(), list, new DefaultRequest());
            linkPrint(serviceResult.getRequestLink());
            return WebResponse.build(serviceResult);
        } catch (Exception e) {
            LogUtil.error(this, e);
            return WebResponse.build(null, "", ResponseCode.ERROR.getMsg(), ResponseCode.ERROR.getText());
        }
    }

    @PostMapping("/getSession")
    @ResponseBody
    public Object getSession(@RequestBody SessionRequest sessionRequest, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute(sessionRequest.getAttrName());
    }

    @PostMapping("/setSession")
    @ResponseBody
    public boolean setSession(@RequestBody SessionRequest sessionRequest, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(sessionRequest.getAttrName(), sessionRequest.getData());
        return true;
    }

    private void linkPrint(LinkNode<String> requestLink) {
        StringBuilder sb = new StringBuilder();
        LinkNode<String> p = requestLink;
        do {
            sb.append(" \n--> ");
            sb.append(p.getData());
            p = p.getLinkNode();
        } while (p != null);
        logger.info(String.format("链路跟踪: %s \n--> 结束!", sb.toString()));
    }

    /**
     * action 添加链路跟踪起点
     *
     * @param action
     */
    private void actionAddRequestLink(@RequestBody Action action) {
        HashMap<String, String> requestLink = new HashMap<>();
        requestLink.put("class", "indi.uhyils.pojo.request.model.LinkNode");
        requestLink.put("data", "页面请求");
        action.getArgs().put("requestLink", requestLink);
    }

}
