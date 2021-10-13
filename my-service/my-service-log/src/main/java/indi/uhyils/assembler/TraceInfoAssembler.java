package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.TraceDetailStatisticsView;
import indi.uhyils.pojo.DO.TraceInfoDO;
import indi.uhyils.pojo.DTO.TraceDetailStatisticsDTO;
import indi.uhyils.pojo.DTO.TraceInfoDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.entity.TraceInfo;
import indi.uhyils.util.BeanUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

/**
 * (TraceInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
@Mapper(componentModel = "spring")
public abstract class TraceInfoAssembler extends AbstractAssembler<TraceInfoDO, TraceInfo, TraceInfoDTO> {

    public TraceDetailStatisticsDTO viewToDTO(TraceDetailStatisticsView view) {
        return BeanUtil.copyProperties(view, TraceDetailStatisticsDTO.class);
    }


    public Page<TraceDetailStatisticsDTO> pageViewToDTO(Page<TraceDetailStatisticsView> result) {
        Page<TraceDetailStatisticsDTO> resultPage = new Page<>();
        BeanUtil.copyProperties(result, resultPage);
        List<TraceDetailStatisticsDTO> dtos = result.getList().stream().map(this::viewToDTO).collect(Collectors.toList());
        resultPage.setList(dtos);
        return resultPage;
    }
}

