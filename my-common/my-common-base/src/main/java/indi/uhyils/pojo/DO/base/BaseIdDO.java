package indi.uhyils.pojo.DO.base;

import indi.uhyils.context.UserContext;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.SpringUtil;
import java.util.Objects;

/**
 * 以id为主键的类都应该继承这个类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月23日 14时23分
 */
public abstract class BaseIdDO implements BaseDbSaveable {

    /**
     * id 一定是uuid的格式
     */
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert(DefaultCQE request) {
        preInsert(request.getUser());
    }

    @Override
    public void preInsert(UserDTO userDO) {
        IdUtil bean = SpringUtil.getBean(IdUtil.class);
        id = bean.newId();
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        preInsert(UserContext.doGet());
    }

    @Override
    public void preUpdate(DefaultCQE request) {
        // 只有id的修改前没有方法,但是也要执行
    }

    @Override
    public void preUpdate(UserDTO userDO) {
    }

    @Override
    public void preUpdate() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return Boolean.TRUE;
        }
        if (o == null || getClass() != o.getClass()) {
            return Boolean.FALSE;
        }
        BaseIdDO that = (BaseIdDO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
