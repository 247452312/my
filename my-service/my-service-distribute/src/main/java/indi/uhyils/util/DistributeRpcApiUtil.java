package indi.uhyils.util;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.util.RpcApiUtil;

import java.util.ArrayList;

/**
 * 组合模块rpc专用
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 15时12分
 */
public class DistributeRpcApiUtil {

    public static ServiceResult<JSONObject> defaultRequest(UserEntity userEntity, String interfaceName, String methodName, DefaultRequest request) {
        ArrayList<Object> args = new ArrayList<>();
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setUser(userEntity);
        defaultRequest.setToken(request.getToken());
        args.add(defaultRequest);
        return (ServiceResult)RpcApiUtil.rpcApiTool(interfaceName, methodName, args, request);
    }
}
