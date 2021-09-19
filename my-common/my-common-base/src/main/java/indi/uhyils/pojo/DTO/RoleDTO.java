package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;
import java.util.List;

/**
 * 角色(Role)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分57秒
 */
public class RoleDTO extends IdDTO {

    private static final long serialVersionUID = -74087407624773308L;


    private Integer level;

    private String name;

    /**
     * 部门们
     */
    private List<DeptDTO> depts;


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeptDTO> getDepts() {
        return depts;
    }

    public void setDepts(List<DeptDTO> depts) {
        this.depts = depts;
    }
}
