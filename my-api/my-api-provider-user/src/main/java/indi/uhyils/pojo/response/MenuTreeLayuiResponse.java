package indi.uhyils.pojo.response;

import indi.uhyils.pojo.response.info.LayuiMenuHomeInfo;
import indi.uhyils.pojo.response.info.LayuiMenuLogoInfo;
import indi.uhyils.pojo.response.info.LayuiMenuMenuInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 后台请求菜单项 layui格式
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时36分
 */
public class MenuTreeLayuiResponse implements Serializable {

    private LayuiMenuHomeInfo homeInfo;

    private LayuiMenuLogoInfo logoInfo;

    private List<LayuiMenuMenuInfo> menuInfo;


    public LayuiMenuHomeInfo getHomeInfo() {
        return homeInfo;
    }

    public void setHomeInfo(LayuiMenuHomeInfo homeInfo) {
        this.homeInfo = homeInfo;
    }

    public LayuiMenuLogoInfo getLogoInfo() {
        return logoInfo;
    }

    public void setLogoInfo(LayuiMenuLogoInfo logoInfo) {
        this.logoInfo = logoInfo;
    }

    public List<LayuiMenuMenuInfo> getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(List<LayuiMenuMenuInfo> menuInfo) {
        this.menuInfo = menuInfo;
    }
}
