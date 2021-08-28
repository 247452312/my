package indi.uhyils.pojo.DTO;


/**
 * 权限(Power)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分49秒
 */
public class PowerDTO extends IdDTO {

    private static final long serialVersionUID = -86835372790023677L;


    private String interfaceName;

    private String methodName;

    private String test;


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


    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

}
