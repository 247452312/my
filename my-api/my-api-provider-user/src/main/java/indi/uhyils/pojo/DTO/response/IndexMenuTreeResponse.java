package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.response.info.IndexMenuInfo;
import indi.uhyils.pojo.DTO.response.info.MenuHomeInfo;
import indi.uhyils.pojo.DTO.response.info.MenuLogoInfo;
import java.io.Serializable;
import java.util.List;

/**
 * 后台请求菜单项
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时36分
 */
public class IndexMenuTreeResponse implements Serializable {

    private MenuHomeInfo homeInfo;

    private MenuLogoInfo logoInfo;

    private List<IndexMenuInfo> menuInfo;


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

    public List<IndexMenuInfo> getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(List<IndexMenuInfo> menuInfo) {
        this.menuInfo = menuInfo;
    }
}
