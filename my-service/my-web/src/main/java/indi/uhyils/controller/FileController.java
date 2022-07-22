package indi.uhyils.controller;

import com.alibaba.fastjson.JSON;
import indi.uhyils.enums.ServiceCode;
import indi.uhyils.pojo.DTO.request.Action;
import indi.uhyils.pojo.DTO.response.WebResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.RpcApiUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月03日 15时05分
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    private static final String INTERFACE = "MongoProvider";

    private static final String METHOD_NAME = "getByFileName";

    private static final String TOKEN = "token";

    @RequestMapping("/down/{fileName}/{token}")
    public String down(@PathVariable String token, @PathVariable String fileName, HttpServletRequest request) {
        String serviceResult = null;
        Map<String, Object> args = new HashMap<>(3);

        try {
            token = new String(Base64.decodeBase64(token));
            args.put("token", token);
            args.put("name", fileName);

            serviceResult = (String) RpcApiUtil.rpcApiTool(INTERFACE, METHOD_NAME, args);

            return serviceResult;
        } catch (Exception e) {
            LogUtil.error(this, e);
            return null;
        } catch (Throwable throwable) {
            LogUtil.error(this, throwable);
            return null;
        }
    }

    @RequestMapping("/up")
    public WebResponse up(@RequestBody Action action, HttpServletRequest request) {
        String methodName = "add";

        LogUtil.info(this, "param: " + JSON.toJSONString(action));
        // token修改到arg中
        action.getArgs().put(TOKEN, action.getToken());

        try {
            action.getArgs().put("token", action.getToken());
            List<Object> list = new ArrayList();
            list.add(action.getArgs());
            String result = (String) RpcApiUtil.rpcApiTool(INTERFACE, methodName, list, new DefaultCQE());

            return WebResponse.build(result, ServiceCode.SUCCESS);
        } catch (Throwable e) {
            LogUtil.error(this, e);
            return null;
        }
    }

}
