package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.DataEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时21分
 */
public class GoodPictureEntity extends DataEntity {

    /**
     * 货物id
     */
    private String goodId;

    /**
     * 图片id
     */
    private String url;

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
