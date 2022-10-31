package indi.uhyils.pojo.entity.wow;

import indi.uhyils.pojo.entity.base.BaseEntity;
import indi.uhyils.pojo.entity.type.Identifier;

/**
 * 天赋
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月26日 10时27分
 */
public interface Talent extends BaseEntity<Identifier> {

    /**
     * 转化为buff
     *
     * @return
     */
    Buff conversionToBuff();
}
