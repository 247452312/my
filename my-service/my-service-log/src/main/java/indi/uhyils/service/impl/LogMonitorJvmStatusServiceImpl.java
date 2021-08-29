package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.LogMonitorJvmStatusAssembler;
import indi.uhyils.pojo.DO.LogMonitorJvmStatusDO;
import indi.uhyils.pojo.DTO.LogMonitorJvmStatusDTO;
import indi.uhyils.pojo.entity.LogMonitorJvmStatus;
import indi.uhyils.repository.LogMonitorJvmStatusRepository;
import indi.uhyils.service.LogMonitorJvmStatusService;
import org.springframework.stereotype.Service;

/**
 * JVM状态子表(LogMonitorJvmStatus)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
@Service
@ReadWriteMark(tables = {"sys_log_monitor_jvm_status"})
public class LogMonitorJvmStatusServiceImpl extends AbstractDoService<LogMonitorJvmStatusDO, LogMonitorJvmStatus, LogMonitorJvmStatusDTO, LogMonitorJvmStatusRepository, LogMonitorJvmStatusAssembler> implements LogMonitorJvmStatusService {


    public LogMonitorJvmStatusServiceImpl(LogMonitorJvmStatusAssembler assembler, LogMonitorJvmStatusRepository repository) {
        super(assembler, repository);
    }

}
