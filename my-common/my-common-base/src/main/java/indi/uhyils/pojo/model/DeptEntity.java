package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoEntity;
import java.util.List;

/**
 * 权限集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时26分
 */
public class DeptEntity extends BaseDoEntity {

    /**
     * 权限集名称
     */
    private String name;

    /**
     * 权限
     */
    private List<PowerEntity> powers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PowerEntity> getPowers() {
        return powers;
    }

    public void setPowers(List<PowerEntity> powers) {
        this.powers = powers;
    }
}
