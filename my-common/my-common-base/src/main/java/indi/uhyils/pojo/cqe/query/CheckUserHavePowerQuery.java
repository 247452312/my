package indi.uhyils.pojo.cqe.query;

import indi.uhyils.pojo.cqe.query.base.AbstractArgQuery;

/**
 * 检查用户是否存在此权限
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月07日 16时40分
 */
public class CheckUserHavePowerQuery extends AbstractArgQuery {

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
    private Long userId;


    public static CheckUserHavePowerQuery build(String interfaceName, String methodName, Long userId) {
        CheckUserHavePowerQuery checkUserHavePowerRequest = new CheckUserHavePowerQuery();
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
