package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.MenuDTO;
import java.io.Serializable;
import java.util.List;

/**
 * 快捷入口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 07时08分
 */
public class QuickStartDTO implements Serializable {

    /**
     * 按钮们 -> 默认都是叶子结点 且不少过8个
     */
    private List<MenuDTO> menus;

    public static QuickStartDTO build(List<MenuDTO> menus) {
        QuickStartDTO quickStartResponse = new QuickStartDTO();
        quickStartResponse.setMenus(menus);
        return quickStartResponse;
    }


    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }
}
