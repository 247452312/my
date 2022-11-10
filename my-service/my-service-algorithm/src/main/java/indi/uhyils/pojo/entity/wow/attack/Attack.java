package indi.uhyils.pojo.entity.wow.attack;

import indi.uhyils.pojo.entity.wow.enemy.Enemy;
import indi.uhyils.pojo.entity.wow.skill.Skill;
import java.util.List;
import java.util.Map;

/**
 * 一次攻击
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 09时00分
 */
public interface Attack {

    /**
     * 伤害到一个怪物
     *
     * @param enemies
     *
     * @return
     */
    Map<Enemy, Double> hartEnemy(List<Enemy> enemies);
    /**
     * 伤害到一个怪物
     *
     * @param enemies
     *
     * @return
     */
    Double hartEnemy(Enemy enemies);


    /**
     * 基于哪个技能发出的
     *
     * @return
     */
    Skill basedOnSkill();

    /**
     * 附加伤害
     *
     * @return
     */
    List<Attack> otherAttack();


}
