package indi.uhyils.pojo.model.base;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 前台可操作性的的数据库实体中都应该有id,创建信息,修改信息删除标志灯信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 13时23分
 */
public class BaseVoEntity extends BaseIdEntity {
    /**
     * 创建时间
     */
    private Integer createDate;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 最后更新时间
     */
    private Integer updateDate;

    /**
     * 更新人
     */
    private Long updateUser;
    /**
     * 删除标志 默认为0
     */
    private Boolean deleteFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert(DefaultRequest request) throws Exception {
        // 这里生成了id
        super.preInsert(request);
        this.createDate = new Long(System.currentTimeMillis() / 1000).intValue();
        this.createUser = request.getUser().getId();
        this.updateDate = this.createDate;
        this.updateUser = this.createUser;
        this.deleteFlag = false;

    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate(DefaultRequest request) {
        super.preUpdate(request);
        this.updateDate = new Long(System.currentTimeMillis() / 1000).intValue();
        this.updateUser = request.getUser().getId();
    }

    public Integer getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Integer updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
