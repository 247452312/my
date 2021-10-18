package indi.uhyils.pojo.entity;

import indi.uhyils.enum_.SourceTypeEnum;
import indi.uhyils.pojo.DO.SourceInfoDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;


/**
 * 资源父类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月17日 12时32分
 */
public abstract class SourceInfo<T extends SourceInfoDO> extends AbstractDoEntity<T> {

    protected SourceInfo(T t) {
        super(t);
    }

    protected SourceInfo() {
    }

    protected SourceInfo(Identifier id, T t) {
        super(id, t);
    }

    protected SourceInfo(Long id, T t) {
        super(id, t);
    }


    /**
     * 资源类型
     *
     * @return
     */
    public SourceTypeEnum type() {
        return toData().getSourceType();
    }

    /**
     * 尝试连接
     *
     * @return
     */
    public abstract Boolean testConnect() throws Exception;

}
