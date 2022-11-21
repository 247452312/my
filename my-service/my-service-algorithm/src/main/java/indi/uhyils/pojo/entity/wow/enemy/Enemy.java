package indi.uhyils.pojo.entity.wow.enemy;

import indi.uhyils.enums.SkillHurtTypeEnum;
import indi.uhyils.pojo.entity.base.BaseEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import java.util.Map;

/**
 * 敌人
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月26日 10时29分
 */
public interface Enemy extends BaseEntity<Identifier> {

    /**
     * 等级
     *
     * @return
     */
    Integer level();

    /**
     * 名称
     *
     * @return
     */
    String name();

    /**
     * 抗性
     *
     * @return
     */
    Map<SkillHurtTypeEnum, Integer> resistanceMap();

    /**
     * 抗性
     *
     * @return
     */
    Integer resistance(SkillHurtTypeEnum hurtType);


}
