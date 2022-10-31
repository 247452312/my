package indi.uhyils.pojo.entity.wow;

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
    Map<Enemy, Integer> hartEnemy(List<Enemy> enemies);


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
