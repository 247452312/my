package indi.uhyils.model;

import indi.uhyils.util.MD5Util;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 13时23分
 */
public class DataEntity implements Serializable {
    /**
     * id 一定是uuid的格式
     */
    private String id;
    /**
     * 创建时间
     */
    private Integer createDate;

    /**
     * 最后更新时间
     */
    private Integer updateDate;
    /**
     * 删除标志 默认为0
     */
    private Boolean delMark;

    /**
     * 备注
     */
    private String remark;

    /**
     * 插入之前执行方法，需要手动调用
     */
    public void preInsert() {
        String uuid = UUID.randomUUID().toString();
        this.id = MD5Util.MD5Encode(uuid);
        this.createDate = new Long(System.currentTimeMillis() / 1000).intValue();
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    public void preUpdate() {
        this.updateDate = new Long(System.currentTimeMillis() / 1000).intValue();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Integer updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getDelMark() {
        return delMark;
    }

    public void setDelMark(Boolean delMark) {
        this.delMark = delMark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
