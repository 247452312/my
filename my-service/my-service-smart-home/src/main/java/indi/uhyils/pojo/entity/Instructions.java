package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.InstructionsDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 说明书表(Instructions)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时04分24秒
 */
public class Instructions extends AbstractDoEntity<InstructionsDO> {

    public Instructions(InstructionsDO dO) {
        super(dO);
    }

    public Instructions(Long id) {
        super(id, new InstructionsDO());
    }

}
