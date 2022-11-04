package indi.uhyils.pojo.entity.wow.skill;

import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.pojo.entity.type.Identifier;

/**
 * 技能
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 16时38分
 */
public abstract class AbstractSkill extends AbstractEntity<Identifier> implements Skill {

    /**
     * 技能等级
     */
    protected Integer level;


    /**
     * 初始化等级
     *
     * @param level
     */
    public void initLevel(Integer level) {
        this.level = level;
    }


}
