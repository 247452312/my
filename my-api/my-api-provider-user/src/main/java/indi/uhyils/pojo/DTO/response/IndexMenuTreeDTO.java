package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.base.BaseDTO;
import indi.uhyils.pojo.DTO.response.info.MenuHomeInfo;
import indi.uhyils.pojo.DTO.response.info.MenuLogoInfo;
import indi.uhyils.pojo.DTO.response.info.MenuTreeDTO;
import java.util.List;

/**
 * 后台请求菜单项
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时36分
 */
public class IndexMenuTreeDTO implements BaseDTO {

    private MenuHomeInfo homeInfo;

    private MenuLogoInfo logoInfo;

    private List<MenuTreeDTO> menuInfo;

    public static IndexMenuTreeDTO build(MenuHomeInfo homeInfo, MenuLogoInfo logoInfo, List<MenuTreeDTO> menuInfo) {
        IndexMenuTreeDTO build = new IndexMenuTreeDTO();
        build.homeInfo = homeInfo;
        build.logoInfo = logoInfo;
        build.menuInfo = menuInfo;
        return build;
    }

    public MenuHomeInfo getHomeInfo() {
        return homeInfo;
    }

    public void setHomeInfo(MenuHomeInfo homeInfo) {
        this.homeInfo = homeInfo;
    }

    public MenuLogoInfo getLogoInfo() {
        return logoInfo;
    }

    public void setLogoInfo(MenuLogoInfo logoInfo) {
        this.logoInfo = logoInfo;
    }

    public List<MenuTreeDTO> getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(List<MenuTreeDTO> menuInfo) {
        this.menuInfo = menuInfo;
    }
}
