package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.LogMonitorAssembler;
import indi.uhyils.dao.LogMonitorDao;
import indi.uhyils.pojo.DO.LogMonitorDO;
import indi.uhyils.pojo.DTO.LogMonitorDTO;
import indi.uhyils.pojo.entity.LogMonitor;
import indi.uhyils.repository.LogMonitorRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.List;


/**
 * JVM日志表(LogMonitor)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
@Repository
public class LogMonitorRepositoryImpl extends AbstractRepository<LogMonitor, LogMonitorDO, LogMonitorDao, LogMonitorDTO, LogMonitorAssembler> implements LogMonitorRepository {


    protected LogMonitorRepositoryImpl(LogMonitorAssembler convert, LogMonitorDao dao) {
        super(convert, dao);
    }


    @Override
    public List<LogMonitor> analysisOnlineService() {
        List<LogMonitorDO> onlineService = dao.getOnlineService(System.currentTimeMillis());
        return assembler.listToEntity(onlineService);
    }

}
