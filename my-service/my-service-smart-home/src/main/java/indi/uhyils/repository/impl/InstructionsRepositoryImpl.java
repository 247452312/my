package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.InstructionsAssembler;
import indi.uhyils.dao.InstructionsDao;
import indi.uhyils.pojo.DO.InstructionsDO;
import indi.uhyils.pojo.DTO.InstructionsDTO;
import indi.uhyils.pojo.entity.Instructions;
import indi.uhyils.repository.InstructionsRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 说明书表(Instructions)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分25秒
 */
@Repository
public class InstructionsRepositoryImpl extends AbstractRepository<Instructions, InstructionsDO, InstructionsDao, InstructionsDTO, InstructionsAssembler> implements InstructionsRepository {

    protected InstructionsRepositoryImpl(InstructionsAssembler convert, InstructionsDao dao) {
        super(convert, dao);
    }


}
