package indi.uhyils.controller;

import indi.uhyils.core.topic.Topic;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.request.LoginRequest;
import indi.uhyils.pojo.request.MqLoginRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.LoginResponse;
import indi.uhyils.pojo.response.WebResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.service.MqInfoService;
import indi.uhyils.service.MqService;
import indi.uhyils.service.UserService;
import indi.uhyils.util.DefaultRequestBuildUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;


/**
 * MQ管理界面
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月30日 08时16分
 */
@RestController
public class MqController {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    @Resource
    private MqService service;

    @Resource
    private MqInfoService mqInfoService;

    @RpcReference
    private UserService userService;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("login")
    public WebResponse<String> login(@RequestBody MqLoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        if (StringUtils.equals(username, USERNAME) && StringUtils.equals(password, PASSWORD)) {
            LoginRequest userRequest = new LoginRequest();
            DefaultRequestBuildUtil.fillRequestByAdminRequest(userRequest);
            userRequest.setUsername("admin");
            userRequest.setPassword("123456");
            ServiceResult<LoginResponse> login = userService.login(userRequest);
            LoginResponse data = login.getData();
            if (data != null && data.getSuccess()) {
                return WebResponse.build(data.getToken(), login.getServiceMessage(), login.getServiceCode());
            }
        }
        return WebResponse.build(null, "登录密码错误,正确用户名密码为:" + USERNAME + "/" + PASSWORD, ServiceCode.SUCCESS.getText());
    }

    /**
     * 获取所有的主题
     *
     * @param request
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @PostMapping("getAllTopic")
    public WebResponse<ArrayList<Topic>> getAllTopic(@RequestBody DefaultRequest request) throws NoSuchFieldException, IllegalAccessException {
        DefaultRequestBuildUtil.fillRequestByAdminRequest(request);
        ServiceResult<ArrayList<Topic>> allInfo = mqInfoService.getAllInfo(request);
        return WebResponse.build(allInfo);
    }

}
