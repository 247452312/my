package indi.uhyils.controller;

import com.alibaba.fastjson.JSON;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.request.Action;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.pojo.response.WebResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.util.DubboApiUtil;
import indi.uhyils.util.LogPushUtils;
import indi.uhyils.util.LogUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static indi.uhyils.controller.AllController.actionAddRequestLink;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月03日 15时05分
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    private static final String INTERFACE = "MongoService";
    private static final String METHOD_NAME = "getByFileName";

    private static final String TOKEN = "token";

    @RequestMapping("/down/{fileName}/{token}")
    public String down(@PathVariable String token, @PathVariable String fileName, HttpServletRequest request) {
        String eMsg = null;
        LinkNode<String> link = null;
        ServiceResult serviceResult = null;
        Map<String, Object> args = new HashMap<>(3);

        try {
            token = new String(Base64.decodeBase64(token));
            args.put("token", token);
            args.put("name", fileName);
            HashMap<String, Object> requestLink = new HashMap<>(2);
            requestLink.put("class", "indi.uhyils.pojo.request.model.LinkNode");
            requestLink.put("data", "页面请求");
            args.put("requestLink", requestLink);
            serviceResult = DubboApiUtil.dubboApiTool(INTERFACE, METHOD_NAME, args);
            link = serviceResult.getRequestLink();
            LogUtil.linkPrint(link);
            if (!serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
                eMsg = serviceResult.getServiceMessage();
            }
            ServiceResult<String> sr = serviceResult;
            return sr.getData();
        } catch (Exception e) {
            LogUtil.error(this, e);
            eMsg = e.getMessage();
            return null;
        } finally {
            if (serviceResult != null) {
                try {
                    LogPushUtils.pushLog(eMsg, INTERFACE, METHOD_NAME, args, link, request, token, serviceResult.getServiceCode());
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }

            }
        }
    }

    @RequestMapping("/up")
    public WebResponse up(@RequestBody Action action, HttpServletRequest request) {
        String methodName = "add";
        String eMsg = null;
        LinkNode<String> link = null;
        ServiceResult serviceResult = null;

        LogUtil.info(this, "param: " + JSON.toJSONString(action));
        // token修改到arg中
        action.getArgs().put(TOKEN, action.getToken());
        // 添加链路跟踪
        actionAddRequestLink(action);

        try {
            action.getArgs().put("token", action.getToken());
            List<Object> list = new ArrayList();
            list.add(action.getArgs());
            serviceResult = DubboApiUtil.dubboApiTool(INTERFACE, methodName, list, new DefaultRequest());
            link = serviceResult.getRequestLink();
            LogUtil.linkPrint(link);
            if (!serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
                eMsg = serviceResult.getServiceMessage();
            }
            return WebResponse.build(serviceResult);
        } catch (Exception e) {
            LogUtil.error(this, e);
            eMsg = e.getMessage();
            return null;
        } finally {
            if (serviceResult != null) {
                try {
                    LogPushUtils.pushLog(eMsg, INTERFACE, methodName, action.getArgs(), link, request, action.getToken(), serviceResult.getServiceCode());
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }

            }
        }
    }

}
