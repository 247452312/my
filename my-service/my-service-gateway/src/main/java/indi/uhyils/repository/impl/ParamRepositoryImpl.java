package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ParamAssembler;
import indi.uhyils.dao.ParamDao;
import indi.uhyils.pojo.DO.ParamDO;
import indi.uhyils.pojo.DTO.ParamDTO;
import indi.uhyils.pojo.entity.Param;
import indi.uhyils.repository.ParamRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 系统参数表(Param)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年06月17日 15时53分
 */
@Repository
public class ParamRepositoryImpl extends AbstractRepository<Param, ParamDO, ParamDao, ParamDTO, ParamAssembler> implements ParamRepository {

    protected ParamRepositoryImpl(ParamAssembler convert, ParamDao dao) {
        super(convert, dao);
    }


}
