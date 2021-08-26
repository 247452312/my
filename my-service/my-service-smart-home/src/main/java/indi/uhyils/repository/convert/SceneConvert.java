package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.Scene;
import indi.uhyils.pojo.DO.SceneDO;

/**
 * 场景表(Scene)表 转换类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分53秒
 */
@Convert
public class SceneConvert extends AbstractDoConvert<Scene, SceneDO> {

    @Override
    public Scene doToEntity(SceneDO dO) {
        return new Scene(dO);
    }
}
