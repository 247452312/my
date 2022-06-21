package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.TraceLogAssembler;
import indi.uhyils.dao.TraceLogDao;
import indi.uhyils.pojo.DO.TraceLogDO;
import indi.uhyils.pojo.DTO.TraceLogDTO;
import indi.uhyils.pojo.entity.TraceLog;
import indi.uhyils.repository.TraceLogRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * (TraceLog)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
@Repository
public class TraceLogRepositoryImpl extends AbstractRepository<TraceLog, TraceLogDO, TraceLogDao, TraceLogDTO, TraceLogAssembler> implements TraceLogRepository {

    protected TraceLogRepositoryImpl(TraceLogAssembler convert, TraceLogDao dao) {
        super(convert, dao);
    }


}
