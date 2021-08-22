package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoDO;
import java.util.List;

/**
 * 权限集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时26分
 */
public class DeptDO extends BaseDoDO {

    /**
     * 权限集名称
     */
    private String name;

    /**
     * 权限
     */
    private List<PowerDO> powers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PowerDO> getPowers() {
        return powers;
    }

    public void setPowers(List<PowerDO> powers) {
        this.powers = powers;
    }
}
