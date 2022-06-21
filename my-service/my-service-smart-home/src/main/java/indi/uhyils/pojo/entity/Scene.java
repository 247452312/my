package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.SceneDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 场景表(Scene)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时04分31秒
 */
public class Scene extends AbstractDoEntity<SceneDO> {

    @Default
    public Scene(SceneDO data) {
        super(data);
    }

    public Scene(Long id) {
        super(id, new SceneDO());
    }

}
