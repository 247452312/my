package indi.uhyils.pojo.model;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时09分
 */
public class GoodEntity extends DataEntity {
    /**
     * 货物
     */
    private String name;

    /**
     * 所剩数量
     */
    private Integer num;

    /**
     * 货物类型
     */
    private String typeId;

    /**
     * 货物类型
     */
    private GoodTypeEntity goodTypeEntity;

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 价格
     */
    private Double price;

    /**
     * 是否打折
     */
    private Boolean isDiscount;

    /**
     * 打折力度
     */
    private Double discountPer;

    /**
     * 用户id(冗余数据,以shopId的userId为准)
     */
    private String userId;

    /**
     * 图片们
     */
    private List<GoodPictureEntity> goodPictureEntities;

    public List<GoodPictureEntity> getGoodPictureEntities() {
        return goodPictureEntities;
    }

    public void setGoodPictureEntities(List<GoodPictureEntity> goodPictureEntities) {
        this.goodPictureEntities = goodPictureEntities;
    }

    public GoodTypeEntity getGoodTypeEntity() {
        return goodTypeEntity;
    }

    public void setGoodTypeEntity(GoodTypeEntity goodTypeEntity) {
        this.goodTypeEntity = goodTypeEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getDiscount() {
        return isDiscount;
    }

    public void setDiscount(Boolean discount) {
        isDiscount = discount;
    }

    public Double getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(Double discountPer) {
        this.discountPer = discountPer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
