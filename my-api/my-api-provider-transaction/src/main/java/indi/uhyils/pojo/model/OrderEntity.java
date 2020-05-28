package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.DataEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时26分
 */
public class OrderEntity extends DataEntity {

    /**
     * 订单状态
     * 0-用户下单 1-商家接单 2-货物已运出 3-货物已送达 4-货物已签收
     */
    private Integer status;

    /**
     * 货物名称
     */
    private String goodName;

    /**
     * 商家id
     */
    private String pushUserId;

    /**
     * 商家名称
     */
    private String pushUserName;

    /**
     * 店铺id
     */
    private String pushShopId;

    /**
     * 店铺名称
     */
    private String pushShopName;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getPushUserId() {
        return pushUserId;
    }

    public void setPushUserId(String pushUserId) {
        this.pushUserId = pushUserId;
    }

    public String getPushUserName() {
        return pushUserName;
    }

    public void setPushUserName(String pushUserName) {
        this.pushUserName = pushUserName;
    }

    public String getPushShopId() {
        return pushShopId;
    }

    public void setPushShopId(String pushShopId) {
        this.pushShopId = pushShopId;
    }

    public String getPushShopName() {
        return pushShopName;
    }

    public void setPushShopName(String pushShopName) {
        this.pushShopName = pushShopName;
    }
}
