package indi.uhyils.controller;

import com.alibaba.fastjson.JSON;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.MongoEntity;
import indi.uhyils.pojo.request.Action;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.pojo.response.WebResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.util.DubboApiUtil;
import indi.uhyils.util.LogPushUtils;
import indi.uhyils.util.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月24日 16时14分
 */
@Controller("/file")
public class FileController {

    private static final String TOKEN = "token";

    /**
     * 文件下载接口
     *
     * @param fileName            文件名称
     * @param request             默认请求
     * @param token               token
     * @param httpServletResponse 返回体
     * @return
     */
    @PostMapping("/down/${token}/${fileName}")
    @ResponseBody
    public void getFile(@PathVariable String fileName, @PathVariable String token, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String eMsg = null;
        LinkNode<String> link = null;
        ServiceResult serviceResult = null;
        Action action = new Action();
        action.setToken(token);
        action.setArgs(new HashMap<>());
        LogUtil.info(this, "param: " + JSON.toJSONString(action));
        action.getArgs().put(TOKEN, action.getToken());
        // 设置文件名称
        action.getArgs().put("name", fileName);
        AllController.actionAddRequestLink(action);
        try {
            List list = new ArrayList();
            list.add(action.getArgs());
            serviceResult = DubboApiUtil.dubboApiTool("MongoService", "getByFileName", list, new DefaultRequest());
            if (ServiceCode.SUCCESS.getText().equals(serviceResult.getServiceCode())) {
                LogUtil.linkPrint(serviceResult.getRequestLink());
                ServiceResult<MongoEntity> sr = serviceResult;
                MongoEntity data = sr.getData();
                byte[] bytes = data.getBytes();
                ServletOutputStream outputStream = httpServletResponse.getOutputStream();
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            }
            link = serviceResult.getRequestLink();
            if (!serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
                eMsg = serviceResult.getServiceMessage();
            }
        } catch (Exception e) {
            LogUtil.error(this, e);
            eMsg = e.getMessage();
        } finally {
            if (serviceResult != null) {
                try {
                    LogPushUtils.pushLog(eMsg, action.getInterfaceName(), action.getMethodName(), action.getArgs(), link, request, action.getToken(), serviceResult.getServiceCode());
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }

            }
        }
    }

    /**
     * 文件上传接口
     *
     * @param file    文件
     * @param action  请求参数
     * @param request 默认请求
     * @return 是否成功
     * @throws IOException
     */
    @PostMapping("/up")
    public WebResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestBody Action action, HttpServletRequest request) throws IOException {
        String eMsg = null;
        LinkNode<String> link = null;
        ServiceResult<String> serviceResult = null;

        LogUtil.info(this, "param: " + JSON.toJSONString(action));
        action.getArgs().put(TOKEN, action.getToken());
        MongoEntity mongoEntity = new MongoEntity();
        mongoEntity.setFileName(file.getName());
        mongoEntity.setBytes(file.getBytes());
        action.getArgs().put("data", mongoEntity);
        AllController.actionAddRequestLink(action);
        try {
            List list = new ArrayList();
            list.add(action.getArgs());
            serviceResult = DubboApiUtil.dubboApiTool("MongoService", "add", list, new DefaultRequest());
            if (ServiceCode.SUCCESS.getText().equals(serviceResult.getServiceCode())) {
                LogUtil.linkPrint(serviceResult.getRequestLink());
            }
            link = serviceResult.getRequestLink();
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
                    LogPushUtils.pushLog(eMsg, action.getInterfaceName(), action.getMethodName(), action.getArgs(), link, request, action.getToken(), serviceResult.getServiceCode());
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }

            }
        }
    }
}
