package indi.uhyils.protocol.task;

import com.alibaba.fastjson.JSON;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.util.RpcApiUtil;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月26日 08时58分
 */
public class JobRunnable implements Callable {

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 调用用户
     */
    private UserDTO userEntity;

    public JobRunnable(String interfaceName, String methodName, String params, String paramType, UserDTO userEntity) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.params = params;
        this.paramType = paramType;
        this.userEntity = userEntity;

    }

    @Override
    public Object call() throws Exception {
        Class<DefaultCQE> aClass = (Class<DefaultCQE>) Class.forName(paramType == null ? "indi.uhyils.pojo.cqe.DefaultCQE" : paramType);
        DefaultCQE defaultRequest = JSON.parseObject(params, aClass);
        defaultRequest.setUser(userEntity);
        ArrayList<Object> list = new ArrayList<>(1);
        list.add(defaultRequest);
        RpcApiUtil.rpcApiToolAsync(interfaceName, methodName, list);
        return Boolean.TRUE;
    }
}
