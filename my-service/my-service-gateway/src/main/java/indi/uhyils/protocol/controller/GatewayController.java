package indi.uhyils.protocol.controller;

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
    public Object postInvoke(HttpServletRequest httpServletRequest) {
        return null;
    }


}
