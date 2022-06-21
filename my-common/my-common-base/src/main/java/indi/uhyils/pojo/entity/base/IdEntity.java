package indi.uhyils.pojo.entity.base;

import indi.uhyils.pojo.entity.type.Identifier;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 16时59分
 */
public interface IdEntity extends BaseEntity<Identifier> {

    /**
     * 是否包含id
     *
     * @return
     */
    boolean haveId();

    /**
     * 是否不包含id
     *
     * @return
     */
    boolean notHaveId();

}
