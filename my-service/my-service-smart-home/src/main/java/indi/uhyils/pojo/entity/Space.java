package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.SpaceDO;

/**
 * 空间坐标表(Space)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时04分35秒
 */
public class Space extends AbstractDoEntity<SpaceDO> {

    public Space(SpaceDO dO) {
        super(dO);
    }

    public Space(Long id) {
        super(id, new SpaceDO());
    }

}
