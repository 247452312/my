package indi.uhyils.pojo.DTO.response.info;

import indi.uhyils.pojo.DTO.BaseDTO;
import indi.uhyils.pojo.DTO.MenuDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时38分
 */
public class MenuTreeDTO implements BaseDTO {

    private MenuDTO menuDTO;

    private List<MenuTreeDTO> child = new ArrayList<>();

    public static MenuTreeDTO build(MenuDTO menuDTO) {
        MenuTreeDTO menuTreeDTO = new MenuTreeDTO();
        menuTreeDTO.setMenuDTO(menuDTO);
        return menuTreeDTO;
    }

    public MenuDTO getMenuDTO() {
        return menuDTO;
    }

    public void setMenuDTO(MenuDTO menuDTO) {
        this.menuDTO = menuDTO;
    }

    public List<MenuTreeDTO> getChild() {
        return child;
    }

    public void setChild(List<MenuTreeDTO> child) {
        this.child = child;
    }
}
