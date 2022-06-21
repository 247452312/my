package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.cqe.DefaultCQE;

/**
 * 获取接口对应的所有方法的请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 07时22分
 */
public class GetMethodNameByInterfaceNameQuery extends DefaultCQE {

    private String interfaceName;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
