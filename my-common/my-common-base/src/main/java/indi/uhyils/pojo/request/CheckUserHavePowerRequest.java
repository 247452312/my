package indi.uhyils.pojo.request;

/**
 * 检查用户是否存在此权限
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月07日 16时40分
 */
public class CheckUserHavePowerRequest extends DefaultRequest {

    /**
     * 权限接口名称
     */
    private String interfaceName;
    /**
     * 权限方法名称
     */
    private String methodName;

    /**
     * 用户id
     */
    private String userId;

    public static CheckUserHavePowerRequest build(String interfaceName, String methodName, String userId) {
        CheckUserHavePowerRequest checkUserHavePowerRequest = new CheckUserHavePowerRequest();
        checkUserHavePowerRequest.setInterfaceName(interfaceName);
        checkUserHavePowerRequest.setMethodName(methodName);
        checkUserHavePowerRequest.setUserId(userId);
        return checkUserHavePowerRequest;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
