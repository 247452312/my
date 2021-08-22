package indi.uhyils.pojo.model.base;

import indi.uhyils.content.UserContent;
import indi.uhyils.pojo.model.UserDO;
import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 前台可操作性的的数据库实体中都应该有id,创建信息,修改信息删除标志灯信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 13时23分
 */
public abstract class BaseDoDO extends BaseIdDO {

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 最后更新时间
     */
    private Long updateDate;

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
    public void preInsert(DefaultRequest request) {
        preInsert(request.getUser());

    }

    @Override
    public void preInsert(UserDO userDO) {
        // 这里生成了id
        super.preInsert(userDO);
        this.createDate = System.currentTimeMillis();
        this.createUser = userDO.getId();
        this.updateDate = this.createDate;
        this.updateUser = this.createUser;
        this.deleteFlag = Boolean.FALSE;
    }

    @Override
    public void preUpdate(UserDO userDO) {
        super.preUpdate(userDO);
        this.updateDate = System.currentTimeMillis();
        this.updateUser = userDO.getId();
    }

    @Override
    public void preUpdate() {
        preUpdate(UserContent.doGet());
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        preInsert(UserContent.doGet());
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate(DefaultRequest request) {
        preUpdate(request.getUser());
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
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

    /**
     * 删除标记
     */
    public void changeToDelete() {
        this.setDeleteFlag(true);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
