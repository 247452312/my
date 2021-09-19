package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import java.util.List;

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

    /**
     * 权限
     */
    private List<PowerDTO> powers;

    /**
     * 菜单
     */
    private List<MenuDTO> menus;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PowerDTO> getPowers() {
        return powers;
    }

    public void setPowers(List<PowerDTO> powers) {
        this.powers = powers;
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }
}
