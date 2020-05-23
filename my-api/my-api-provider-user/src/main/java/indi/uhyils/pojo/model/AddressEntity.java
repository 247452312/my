package indi.uhyils.pojo.model;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 12时44分
 */
public class AddressEntity extends DataEntity {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 地址
     */
    private String address;

    /**
     * 1-家 2-公司 3-临时
     */
    private Integer type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
