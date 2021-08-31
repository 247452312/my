package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.mq.pojo.mqinfo.JvmStatusInfoEvent;
import indi.uhyils.pojo.DO.LogMonitorJvmStatusDO;
import indi.uhyils.pojo.DTO.LogMonitorJvmStatusDTO;
import indi.uhyils.pojo.entity.LogMonitorJvmStatus;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JVM状态子表(LogMonitorJvmStatus)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
@Assembler
public class LogMonitorJvmStatusAssembler extends AbstractAssembler<LogMonitorJvmStatusDO, LogMonitorJvmStatus, LogMonitorJvmStatusDTO> {

    @Override
    public LogMonitorJvmStatus toEntity(LogMonitorJvmStatusDO dO) {
        return new LogMonitorJvmStatus(dO);
    }

    @Override
    public LogMonitorJvmStatus toEntity(LogMonitorJvmStatusDTO dto) {
        return new LogMonitorJvmStatus(toDo(dto));
    }

    @Override
    protected Class<LogMonitorJvmStatusDO> getDoClass() {
        return LogMonitorJvmStatusDO.class;
    }

    @Override
    protected Class<LogMonitorJvmStatusDTO> getDtoClass() {
        return LogMonitorJvmStatusDTO.class;
    }

    public LogMonitorJvmStatus jvmStatusInfoToEntity(JvmStatusInfoEvent jvmStatusInfo) {
        LogMonitorJvmStatusDO logMonitorJvmStatusDO = transJvmStatusInfoToMonitorJvmStatusDetailDO(jvmStatusInfo, null);
        return new LogMonitorJvmStatus(logMonitorJvmStatusDO, jvmStatusInfo.getJvmUniqueMark());
    }

    public LogMonitorJvmStatusDO transJvmStatusInfoToMonitorJvmStatusDetailDO(JvmStatusInfoEvent jvmStatusInfo, Long fid) {
        LogMonitorJvmStatusDO logMonitorJvmStatusEntity = new LogMonitorJvmStatusDO();
        logMonitorJvmStatusEntity.setFid(fid);
        logMonitorJvmStatusEntity.setHeapUseMem(jvmStatusInfo.getHeapUseMem());
        logMonitorJvmStatusEntity.setNoHeapUseMem(jvmStatusInfo.getNoHeapUseMem());
        logMonitorJvmStatusEntity.setTime(jvmStatusInfo.getTime());
        logMonitorJvmStatusEntity.setUseMem(jvmStatusInfo.getTotalUseMem());
        return logMonitorJvmStatusEntity;
    }

    public List<LogMonitorJvmStatusDO> transJvmStatusInfosToMonitorJvmStatusDetailDOs(List<JvmStatusInfoEvent> jvmStatusInfos, Long fid) {
        return jvmStatusInfos.stream().map(jvmStatusInfo -> transJvmStatusInfoToMonitorJvmStatusDetailDO(jvmStatusInfo, fid)).collect(Collectors.toList());
    }

}

