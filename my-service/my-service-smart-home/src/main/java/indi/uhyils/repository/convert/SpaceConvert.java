package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.Space;
import indi.uhyils.pojo.DO.SpaceDO;

/**
 * 空间坐标表(Space)表 转换类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分58秒
 */
@Convert
public class SpaceConvert extends AbstractDoConvert<Space, SpaceDO> {

    @Override
    public Space doToEntity(SpaceDO dO) {
        return new Space(dO);
    }
}
