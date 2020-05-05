package indi.uhyils.model;

/**
 * 店铺
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 11时59分
 */
public class ShopEntity extends DataEntity {


    /**
     * 店铺所属用户id
     */
    private String userId;

    /**
     * 名称
     */
    private String name;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
