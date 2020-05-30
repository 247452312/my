package indi.uhyils.pojo.response.info;

import indi.uhyils.pojo.model.ContentEntity;
import indi.uhyils.util.ContentUtil;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时38分
 */
public class LayuiMenuHomeInfo implements Serializable {
    private static final String HOME_INFO = "homeInfo";
    private String title;
    private String href;

    public static LayuiMenuHomeInfo build(ContentEntity honeInfo) {
        assert HOME_INFO.equals(honeInfo.getName());

        LayuiMenuHomeInfo layuiMenuHomeInfo = new LayuiMenuHomeInfo();
        layuiMenuHomeInfo.setTitle(ContentUtil.getContentVarByTitle(honeInfo, "title"));
        layuiMenuHomeInfo.setHref(ContentUtil.getContentVarByTitle(honeInfo, "href"));
        return layuiMenuHomeInfo;
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
