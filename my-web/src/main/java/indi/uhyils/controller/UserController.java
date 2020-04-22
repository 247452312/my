package indi.uhyils.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import indi.uhyils.model.User;
import indi.uhyils.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月22日 09时07分
 */
@Controller
@Component("UserController")
@RequestMapping("user")
public class UserController {


    @Reference
    private UserService userService;


    @RequestMapping("getUserById")
    @ResponseBody
    public User getUserById(Integer userId) {
        return userService.getById(userId);
    }
}
