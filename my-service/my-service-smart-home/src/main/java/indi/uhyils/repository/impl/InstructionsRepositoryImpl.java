package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.InstructionsConvert;
import indi.uhyils.dao.InstructionsDao;
import indi.uhyils.pojo.entity.Instructions;
import indi.uhyils.pojo.DO.InstructionsDO;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.repository.InstructionsRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 说明书表(Instructions)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分42秒
 */
@Repository
public class InstructionsRepositoryImpl extends AbstractRepository<Instructions, InstructionsDO, InstructionsDao> implements InstructionsRepository {

    protected InstructionsRepositoryImpl(InstructionsConvert convert, InstructionsDao dao) {
        super(convert, dao);
    }


}
