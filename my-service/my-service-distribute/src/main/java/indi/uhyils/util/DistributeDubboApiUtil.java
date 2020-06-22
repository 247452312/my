package indi.uhyils.util;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.response.ServiceResult;

import java.util.ArrayList;

/**
 * 组合模块dubbo专用
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 15时12分
 */
public class DistributeDubboApiUtil {

    public static ServiceResult<JSONObject> defaultRequest(String interfaceName, String methodName, DefaultRequest request) {
        ArrayList<Object> args = new ArrayList<>();
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken(request.getToken());
        defaultRequest.setRequestLink(request.getRequestLink());
        args.add(defaultRequest);
        return DubboApiUtil.dubboApiTool(interfaceName, methodName, args, request);
    }
}
