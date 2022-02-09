package indi.uhyils.assembler;


import indi.uhyils.mq.pojo.mqinfo.JvmStatusInfoCommand;
import indi.uhyils.pojo.DO.LogMonitorJvmStatusDO;
import indi.uhyils.pojo.DTO.LogMonitorJvmStatusDTO;
import indi.uhyils.pojo.entity.LogMonitorJvmStatus;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * JVM状态子表(LogMonitorJvmStatus)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
@Mapper(componentModel = "spring")
public abstract class LogMonitorJvmStatusAssembler extends AbstractAssembler<LogMonitorJvmStatusDO, LogMonitorJvmStatus, LogMonitorJvmStatusDTO> {

    /**
     * JVM状态信息转换为对应entity
     *
     * @param jvmStatusInfo
     *
     * @return
     */
    public LogMonitorJvmStatus jvmStatusInfoToEntity(JvmStatusInfoCommand jvmStatusInfo) {
        LogMonitorJvmStatusDO logMonitorJvmStatusDO = transJvmStatusInfoToMonitorJvmStatusDetailDO(jvmStatusInfo, null);
        return new LogMonitorJvmStatus(logMonitorJvmStatusDO, jvmStatusInfo.getJvmUniqueMark());
    }

    /**
     * JVM状态信息复制到JVM状态DO中
     *
     * @param jvmStatusInfo
     * @param fid
     *
     * @return
     */
    @Mapping(source = "jvmStatusInfo.totalUseMem", target = "useMem")
    public abstract LogMonitorJvmStatusDO transJvmStatusInfoToMonitorJvmStatusDetailDO(JvmStatusInfoCommand jvmStatusInfo, Long fid);

    /**
     * 多个JVM状态信息复制到JVM状态DO中
     *
     * @param jvmStatusInfos
     * @param fid
     *
     * @return
     */
    public List<LogMonitorJvmStatusDO> transJvmStatusInfosToMonitorJvmStatusDetailDOs(List<JvmStatusInfoCommand> jvmStatusInfos, Long fid) {
        return jvmStatusInfos.stream().map(jvmStatusInfo -> transJvmStatusInfoToMonitorJvmStatusDetailDO(jvmStatusInfo, fid)).collect(Collectors.toList());
    }

}

