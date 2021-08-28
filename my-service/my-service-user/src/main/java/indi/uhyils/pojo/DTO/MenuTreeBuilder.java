package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.response.info.MenuTreeDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 16时19分
 */
public class MenuTreeBuilder {

    /**
     * 最高一级菜单的fid
     */
    private static final Long NONE = 0L;

    private List<MenuDTO> menuDTO;

    public MenuTreeBuilder() {
        this.menuDTO = new ArrayList<>();
    }

    public void addMenu(List<MenuDTO> menuDTO) {
        this.menuDTO.addAll(menuDTO);
    }

    public void addMenu(MenuDTO menuDTO) {
        this.menuDTO.add(menuDTO);
    }

    public List<MenuTreeDTO> build() {
        return buildMenuTree(this.menuDTO);
    }

    public MenuTreeDTO buildSingle() {
        return buildMenuTree(this.menuDTO).get(0);
    }

    private List<MenuTreeDTO> buildMenuTree(List<MenuDTO> menus) {
        List<MenuTreeDTO> menuInfo = new ArrayList<>();

        // 父节点都找出来
        for (MenuDTO menuEntity : menus) {
            if (NONE.equals(menuEntity.getFid())) {
                menuInfo.add(MenuTreeDTO.build(menuEntity));
            }
        }
        //每一个父节点都添加属于自己的树
        for (MenuTreeDTO treeResponse : menuInfo) {
            addChild(treeResponse, menus);
        }
        return menuInfo;
    }


    /**
     * 递归添加子结点
     *
     * @param treeResponse 父节点
     * @param byIFrame     全部有用节点
     */
    private void addChild(MenuTreeDTO treeResponse, List<MenuDTO> byIFrame) {
        for (MenuDTO menuEntity : byIFrame) {
            if (menuEntity.getFid().equals(treeResponse.getMenuDTO().getId())) {
                MenuTreeDTO build = MenuTreeDTO.build(menuEntity);
                addChild(build, byIFrame);
                treeResponse.getChild().add(build);
            }
        }
    }
}
