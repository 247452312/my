package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.LogMonitorAssembler;
import indi.uhyils.pojo.DO.LogMonitorDO;
import indi.uhyils.pojo.DTO.LogMonitorDTO;
import indi.uhyils.pojo.DTO.response.JvmDataStatisticsDTO;
import indi.uhyils.pojo.DTO.response.JvmInfoLogDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.entity.LogMonitor;
import indi.uhyils.pojo.entity.OnlineMonitors;
import indi.uhyils.repository.LogMonitorJvmStatusRepository;
import indi.uhyils.repository.LogMonitorRepository;
import indi.uhyils.repository.TraceInfoRepository;
import indi.uhyils.service.LogMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LogMonitorJvmStatusRepository logMonitorJvmStatusRepository;

    @Autowired
    private TraceInfoRepository traceInfoRepository;


    public LogMonitorServiceImpl(LogMonitorAssembler assembler, LogMonitorRepository repository) {
        super(assembler, repository);
    }

    @Override
    public JvmDataStatisticsDTO getJvmDataStatisticsResponse(DefaultCQE request) {
        /* 获取在线服务 */
        OnlineMonitors onlineMonitor = new OnlineMonitors(rep);
        /* 获取服务运行质量(以外关闭的系统,内存溢出风险的系统) */
        onlineMonitor.initServiceQuality(logMonitorJvmStatusRepository);
        /*获取web调用次数*/
        onlineMonitor.initWebRequestCount(traceInfoRepository);
        /*获取rpc调用次数*/
        onlineMonitor.initRpcExecuteCount(traceInfoRepository);
        return onlineMonitor.outputJvmDataStatictics();
    }

    @Override
    public JvmInfoLogDTO getJvmInfoLogResponse(DefaultCQE request) {
        /*获取所有的活着的服务的监控信息*/
        OnlineMonitors onlineMonitors = new OnlineMonitors(rep);
        /*初始化历史状态*/
        onlineMonitors.initServiceQuality(logMonitorJvmStatusRepository);
        /*做echart图*/
        return onlineMonitors.makeEchart(logMonitorJvmStatusRepository);
    }
}
