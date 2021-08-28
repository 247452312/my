package indi.uhyils.pojo.entity.type;

import indi.uhyils.repository.PowerRepository;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月28日 13时21分
 */
public class InterfaceName implements BaseType {

    /**
     * 接口名称
     */
    private String interfaceName;


    public InterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public List<MethodName> getMethods(PowerRepository rep) {
        return rep.getMethodNameByInterfaceName(this);
    }

}
