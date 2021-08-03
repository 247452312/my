package indi.uhyils.pojo.model.base;

import indi.uhyils.exception.IdGenerationException;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.util.DefaultRequestBuildUtil;

/**
 * 前台可操作性的的数据库实体中都应该有id,创建信息,修改信息删除标志灯信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 13时23分
 */
public abstract class BaseDoEntity extends BaseIdEntity {

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
    public void preInsert(DefaultRequest request) throws IdGenerationException, InterruptedException {
        // 这里生成了id
        super.preInsert(request);
        this.createDate = System.currentTimeMillis();
        this.createUser = request.getUser().getId();
        this.updateDate = this.createDate;
        this.updateUser = this.createUser;
        this.deleteFlag = Boolean.FALSE;

    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() throws IdGenerationException, InterruptedException {
        DefaultRequest request = DefaultRequestBuildUtil.getAdminDefaultRequest();
        preInsert(request);

    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate(DefaultRequest request) {
        super.preUpdate(request);
        this.updateDate = System.currentTimeMillis();
        this.updateUser = request.getUser().getId();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
