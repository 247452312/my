package indi.uhyils.request;



/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 10时21分
 */
public class Action {
    private String interfaceName;
    private String methodName;
    private Object args;


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

    public Object getArgs() {
        return args;
    }

    public void setArgs(Object args) {
        this.args = args;
    }
}
