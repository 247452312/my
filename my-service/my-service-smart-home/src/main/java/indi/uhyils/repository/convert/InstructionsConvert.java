package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.Instructions;
import indi.uhyils.pojo.DO.InstructionsDO;

/**
 * 说明书表(Instructions)表 转换类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分42秒
 */
@Convert
public class InstructionsConvert extends AbstractDoConvert<Instructions, InstructionsDO> {

    @Override
    public Instructions doToEntity(InstructionsDO dO) {
        return new Instructions(dO);
    }
}
