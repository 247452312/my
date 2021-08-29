package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.TraceLogDO;
import indi.uhyils.pojo.DTO.TraceLogDTO;
import indi.uhyils.pojo.entity.TraceLog;

/**
 * (TraceLog)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
@Assembler
public class TraceLogAssembler extends AbstractAssembler<TraceLogDO, TraceLog, TraceLogDTO> {

    @Override
    public TraceLog toEntity(TraceLogDO dO) {
        return new TraceLog(dO);
    }

    @Override
    public TraceLog toEntity(TraceLogDTO dto) {
        return new TraceLog(toDo(dto));
    }

    @Override
    protected Class<TraceLogDO> getDoClass() {
        return TraceLogDO.class;
    }

    @Override
    protected Class<TraceLogDTO> getDtoClass() {
        return TraceLogDTO.class;
    }
}

