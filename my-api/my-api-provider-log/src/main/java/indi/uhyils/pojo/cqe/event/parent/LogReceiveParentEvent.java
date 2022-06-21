package indi.uhyils.pojo.cqe.event.parent;

import indi.uhyils.pojo.DTO.TraceDetailDTO;
import indi.uhyils.pojo.cqe.event.CheckAndAddRelegationEvent;
import indi.uhyils.pojo.cqe.event.base.AbstractParentEvent;
import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import java.util.Arrays;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 08时53分
 */
public class LogReceiveParentEvent extends AbstractParentEvent {

    /**
     * 详情
     */
    private final TraceDetailDTO traceDetailDTO;

    public LogReceiveParentEvent(TraceDetailDTO traceDetailDTO) {
        this.traceDetailDTO = traceDetailDTO;
    }

    @Override
    protected List<BaseEvent> transToBaseEvent0() {
        return Arrays.asList(
            // 接口自动降级检查与添加
            new CheckAndAddRelegationEvent(traceDetailDTO)
        );
    }

}
