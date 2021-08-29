package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.LogMonitorDO;
import indi.uhyils.pojo.DTO.LogMonitorDTO;
import indi.uhyils.pojo.entity.LogMonitor;

/**
 * JVM日志表(LogMonitor)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
@Assembler
public class LogMonitorAssembler extends AbstractAssembler<LogMonitorDO, LogMonitor, LogMonitorDTO> {

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
}

