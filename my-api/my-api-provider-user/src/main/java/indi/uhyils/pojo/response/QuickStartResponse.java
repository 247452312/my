package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.MenuDO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 快捷入口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 07时08分
 */
public class QuickStartResponse implements Serializable {

    /**
     * 按钮们 -> 默认都是叶子结点 且不少过8个
     */
    private List<MenuDO> menus;

    public static QuickStartResponse build(ArrayList<MenuDO> menus) {
        QuickStartResponse quickStartResponse = new QuickStartResponse();
        quickStartResponse.setMenus(menus);
        return quickStartResponse;
    }


    public List<MenuDO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDO> menus) {
        this.menus = menus;
    }
}
