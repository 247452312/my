package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoDO;
import java.util.List;

/**
 * 用户角色
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时24分
 */
public class RoleDO extends BaseDoDO {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色等级 1是最高
     */
    private Integer level;

    /**
     * 权限集
     */
    private List<DeptDO> depts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<DeptDO> getDepts() {
        return depts;
    }

    public void setDepts(List<DeptDO> depts) {
        this.depts = depts;
    }
}
