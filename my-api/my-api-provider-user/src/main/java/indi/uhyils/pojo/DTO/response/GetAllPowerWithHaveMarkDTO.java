package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.BaseDTO;

/**
 * 获取所有的权限(携带羁绊登场)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月07日 13时58分
 */
public class GetAllPowerWithHaveMarkDTO implements BaseDTO {

    /**
     * 权限id
     */
    private Long powerId;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 标记
     */
    private Boolean mark;


    public Long getPowerId() {
        return powerId;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
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

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }
}
