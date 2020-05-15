package indi.uhyils.controller;

import indi.uhyils.request.Action;
import indi.uhyils.response.WebResponse;
import indi.uhyils.util.DubboApiUtil;
import indi.uhyils.util.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月24日 16时14分
 */
@Controller
public class AllController {


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
            return WebResponse.build(DubboApiUtil.dubboApiTool(action.getInterfaceName(), action.getMethodName(), list));
        } catch (ClassNotFoundException e) {
            LogUtil.error(this, e);
            return WebResponse.build(null, "", e.getMessage(), 500);
        }
    }

    /**
     * action 添加链路跟踪起点
     *
     * @param action
     */
    private void actionAddRequestLink(@RequestBody Action action) {
        HashMap<String, String> requestLink = new HashMap<>();
        requestLink.put("class", "indi.uhyils.request.model.LinkNode");
        requestLink.put("data", "页面请求");
        action.getArgs().put("requestLink", requestLink);
    }

}
