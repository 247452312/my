package indi.uhyils.pojo.DTO.response.info;

import indi.uhyils.pojo.DTO.ContentDTO;
import indi.uhyils.util.ContentUtil;
import java.io.Serializable;

/**
 * 主页logo信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时38分
 */
public class MenuLogoInfo implements Serializable {

    private String title;

    private String image;

    private String href;

    public static MenuLogoInfo build(ContentDTO logoInfo) {
        MenuLogoInfo menuLogoInfo = new MenuLogoInfo();
        menuLogoInfo.setHref(ContentUtil.getContentVarByTitle(logoInfo, "href"));
        menuLogoInfo.setImage(ContentUtil.getContentVarByTitle(logoInfo, "image"));
        menuLogoInfo.setTitle(ContentUtil.getContentVarByTitle(logoInfo, "title"));
        return menuLogoInfo;
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
