package indi.uhyils.pojo.response.info;

import indi.uhyils.pojo.model.ContentEntity;
import indi.uhyils.util.ContentUtil;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时38分
 */
public class LayuiMenuLogoInfo implements Serializable {

    private String title;
    private String image;
    private String href;

    public static LayuiMenuLogoInfo build(ContentEntity logoInfo) {
        LayuiMenuLogoInfo layuiMenuLogoInfo = new LayuiMenuLogoInfo();
        layuiMenuLogoInfo.setHref(ContentUtil.getContentVarByTitle(logoInfo, "href"));
        layuiMenuLogoInfo.setImage(ContentUtil.getContentVarByTitle(logoInfo, "image"));
        layuiMenuLogoInfo.setTitle(ContentUtil.getContentVarByTitle(logoInfo, "title"));
        return layuiMenuLogoInfo;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
