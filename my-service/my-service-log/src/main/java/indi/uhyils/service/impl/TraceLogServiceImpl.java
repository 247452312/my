package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.TraceLogAssembler;
import indi.uhyils.pojo.DO.TraceLogDO;
import indi.uhyils.pojo.DTO.TraceLogDTO;
import indi.uhyils.pojo.entity.TraceLog;
import indi.uhyils.repository.TraceLogRepository;
import indi.uhyils.service.TraceLogService;
import org.springframework.stereotype.Service;

/**
 * (TraceLog)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
@Service
@ReadWriteMark(tables = {"sys_trace_log"})
public class TraceLogServiceImpl extends AbstractDoService<TraceLogDO, TraceLog, TraceLogDTO, TraceLogRepository, TraceLogAssembler> implements TraceLogService {

    public TraceLogServiceImpl(TraceLogAssembler assembler, TraceLogRepository repository) {
        super(assembler, repository);
    }

}
