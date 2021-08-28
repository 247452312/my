package indi.uhyils.pojo.DTO;

/**
 * 部门
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 18时08分
 */
public class DeptDTO extends IdDTO {

    private static final long serialVersionUID = 892336391522306220L;

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
