package indi.uhyils.pojo.entity.base;

import indi.uhyils.pojo.DO.base.BaseDO;
import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月24日 16时05分
 */
public interface DoEntity<T extends BaseDO> extends IdEntity {

    /**
     * 获取Do
     *
     * @return
     */
    Optional<T> toData();
}
