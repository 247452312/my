package indi.uhyils.controller;

import indi.uhyils.enum_.UserTypeEnum;
import indi.uhyils.request.Action;
import indi.uhyils.request.GetUserRequest;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.response.WebResponse;
import indi.uhyils.util.DubboApiUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.TouristUserIdMakeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

        //验证登录情况 如果未登录 则获取游客token
        action.getArgs().put("token", action.getToken());
        try {
            Object args = action.getArgs();
            List list = new ArrayList();
            list.add(args);
            return WebResponse.build(DubboApiUtil.dubboApiTool(action.getInterfaceName(), action.getMethodName(), list));
        } catch (ClassNotFoundException e) {
            LogUtil.error(this, e);
        }
        return WebResponse.build(null, "1", "msg", 404);
    }

    /**
     * 游客token注入
     *
     * @param action
     */
    private void touristUserIdInject(@RequestBody Action action) {
        String touristUserId = TouristUserIdMakeUtil.getTouristUserId();
        ArrayList<Object> args1 = new ArrayList<>();
        GetUserRequest build = GetUserRequest.build(touristUserId, UserTypeEnum.TOURIST);
        args1.add(build);
        try {
            //获取游客token
            ServiceResult<String> serviceResult = DubboApiUtil.dubboApiTool("UserService", "getUserTokenNoToken", args1);
            String touriseToken = serviceResult.getData();
            action.getArgs().put("token", touriseToken);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
