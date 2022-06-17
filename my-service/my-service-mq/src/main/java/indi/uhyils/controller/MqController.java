package indi.uhyils.controller;

import indi.uhyils.core.topic.Topic;
import indi.uhyils.enums.ServiceCode;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.request.MqLoginRequest;
import indi.uhyils.pojo.DTO.response.LoginDTO;
import indi.uhyils.pojo.DTO.response.WebResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.UserProvider;
import indi.uhyils.protocol.rpc.provider.MqInfoService;
import indi.uhyils.protocol.rpc.provider.MqProvider;
import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.util.DefaultCQEBuildUtil;
import java.util.ArrayList;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
    private MqProvider service;

    @Resource
    private MqInfoService mqInfoService;

    @RpcReference
    private UserProvider userService;

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
            LoginCommand userRequest = new LoginCommand();
            DefaultCQEBuildUtil.fillRequestByAdminRequest(userRequest);
            userRequest.setUsername("admin");
            userRequest.setPassword("123456");
            ServiceResult<LoginDTO> login = userService.login(userRequest);
            LoginDTO data = login.getData();
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
     *
     * @return
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @PostMapping("getAllTopic")
    public WebResponse<ArrayList<Topic>> getAllTopic(@RequestBody DefaultCQE request) throws NoSuchFieldException, IllegalAccessException {
        DefaultCQEBuildUtil.fillRequestByAdminRequest(request);
        ServiceResult<ArrayList<Topic>> allInfo = mqInfoService.getAllInfo(request);
        return WebResponse.build(allInfo);
    }

}
