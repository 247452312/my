package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.pojo.mqinfo.JvmStartInfoEvent;
import indi.uhyils.mq.pojo.mqinfo.JvmStatusInfoEvent;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.pojo.DO.LogMonitorDO;
import indi.uhyils.pojo.DO.LogMonitorJvmStatusDO;
import indi.uhyils.pojo.DTO.LogMonitorDTO;
import indi.uhyils.pojo.entity.LogMonitor;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JVM日志表(LogMonitor)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
@Assembler
public class LogMonitorAssembler extends AbstractAssembler<LogMonitorDO, LogMonitor, LogMonitorDTO> {

    @Autowired
    private LogMonitorJvmStatusAssembler logMonitorJvmStatusAssembler;

    @Override
    public LogMonitor toEntity(LogMonitorDO dO) {
        return new LogMonitor(dO);
    }

    @Override
    public LogMonitor toEntity(LogMonitorDTO dto) {
        return new LogMonitor(toDo(dto));
    }

    @Override
    protected Class<LogMonitorDO> getDoClass() {
        return LogMonitorDO.class;
    }

    @Override
    protected Class<LogMonitorDTO> getDtoClass() {
        return LogMonitorDTO.class;
    }

    public LogMonitor jvmStartInfoToLogMonitor(JvmStartInfoEvent jvmStartInfo) {
        JvmUniqueMark jvmUniqueMark = jvmStartInfo.getJvmUniqueMark();
        LogMonitorDO logMonitorDO = transJvmStartInfoToMonitorDO(jvmStartInfo);
        List<JvmStatusInfoEvent> jvmStatusInfos = jvmStartInfo.getJvmStatusInfos();
        List<LogMonitorJvmStatusDO> logMonitorJvmStatusEntities = logMonitorJvmStatusAssembler.transJvmStatusInfosToMonitorJvmStatusDetailDOs(jvmStatusInfos, null);
        return new LogMonitor(jvmUniqueMark, logMonitorDO, logMonitorJvmStatusEntities);
    }


    public LogMonitorDO transJvmStartInfoToMonitorDO(JvmStartInfoEvent jvmStartInfo) {
        LogMonitorDO logMonitorEntity = new LogMonitorDO();
        JvmUniqueMark jvmUniqueMark = jvmStartInfo.getJvmUniqueMark();
        logMonitorEntity.setIp(jvmUniqueMark.getIp());
        logMonitorEntity.setServiceName(jvmUniqueMark.getServiceName());
        Long time = jvmUniqueMark.getTime();
        logMonitorEntity.setTime(time);

        //设置假想结束时间=JVM上次发送状态时间+ OUT_TIME*比例系数
        double v = time + RabbitMqContent.OUT_TIME * 60 * 1000 * RabbitMqContent.OUT_TIME_PRO;
        logMonitorEntity.setEndTime(new Double(v).longValue());
        logMonitorEntity.setJvmTotalMem(jvmStartInfo.getJvmTotalMem());
        logMonitorEntity.setHeapTotalMem(jvmStartInfo.getHeapTotalMem());
        logMonitorEntity.setHeapInitMem(jvmStartInfo.getHeapInitMem());
        logMonitorEntity.setNoHeapTotalMem(jvmStartInfo.getNoHeapTotalMem());
        logMonitorEntity.setNoHeapInitMem(jvmStartInfo.getNoHeapInitMem());
        return logMonitorEntity;
    }



}

