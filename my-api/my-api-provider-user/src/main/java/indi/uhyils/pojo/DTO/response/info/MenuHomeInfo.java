package indi.uhyils.pojo.DTO.response.info;

import indi.uhyils.pojo.DTO.ContentDTO;
import indi.uhyils.util.ContentUtil;
import java.io.Serializable;

/**
 * index页面的主页设置
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时38分
 */
public class MenuHomeInfo implements Serializable {

    private static final String HOME_INFO = "homeInfo";

    private String title;

    private String href;

    public static MenuHomeInfo build(ContentDTO honeInfo) {
        assert HOME_INFO.equals(honeInfo.getName());

        MenuHomeInfo menuHomeInfo = new MenuHomeInfo();
        menuHomeInfo.setTitle(ContentUtil.getContentVarByTitle(honeInfo, "title"));
        menuHomeInfo.setHref(ContentUtil.getContentVarByTitle(honeInfo, "href"));
        return menuHomeInfo;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
