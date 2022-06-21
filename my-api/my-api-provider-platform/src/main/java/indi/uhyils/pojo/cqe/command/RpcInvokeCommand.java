package indi.uhyils.pojo.cqe.command;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.pojo.cqe.command.base.AbstractCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月21日 09时21分
 */
public class RpcInvokeCommand extends AbstractCommand {


    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 入参
     */
    private JSONArray param;


    public static RpcInvokeCommand build(String interfaceName, String methodName, JSONArray param) {
        RpcInvokeCommand build = new RpcInvokeCommand();
        build.setParam(param);
        build.setInterfaceName(interfaceName);
        build.setMethodName(methodName);
        return build;

    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public JSONArray getParam() {
        return param;
    }

    public void setParam(JSONArray param) {
        this.param = param;
    }
}
