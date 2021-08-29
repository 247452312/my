package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.LogMonitorAssembler;
import indi.uhyils.pojo.DO.LogMonitorDO;
import indi.uhyils.pojo.DTO.LogMonitorDTO;
import indi.uhyils.pojo.entity.LogMonitor;
import indi.uhyils.repository.LogMonitorRepository;
import indi.uhyils.service.LogMonitorService;
import org.springframework.stereotype.Service;

/**
 * JVM日志表(LogMonitor)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
@Service
@ReadWriteMark(tables = {"sys_log_monitor"})
public class LogMonitorServiceImpl extends AbstractDoService<LogMonitorDO, LogMonitor, LogMonitorDTO, LogMonitorRepository, LogMonitorAssembler> implements LogMonitorService {

    public LogMonitorServiceImpl(LogMonitorAssembler assembler, LogMonitorRepository repository) {
        super(assembler, repository);
    }


}
