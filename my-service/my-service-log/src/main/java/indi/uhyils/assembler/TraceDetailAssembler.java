package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.TraceDetailDO;
import indi.uhyils.pojo.DTO.TraceDetailDTO;
import indi.uhyils.pojo.entity.TraceDetail;

/**
 * (TraceDetail)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分54秒
 */
@Assembler
public class TraceDetailAssembler extends AbstractAssembler<TraceDetailDO, TraceDetail, TraceDetailDTO> {

    @Override
    public TraceDetail toEntity(TraceDetailDO dO) {
        return new TraceDetail(dO);
    }

    @Override
    public TraceDetail toEntity(TraceDetailDTO dto) {
        return new TraceDetail(toDo(dto));
    }

    @Override
    protected Class<TraceDetailDO> getDoClass() {
        return TraceDetailDO.class;
    }

    @Override
    protected Class<TraceDetailDTO> getDtoClass() {
        return TraceDetailDTO.class;
    }
}

