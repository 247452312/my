package indi.uhyils.protocol.register.impl;

import indi.uhyils.enums.LogTypeEnum;
import indi.uhyils.pojo.DTO.TraceDetailDTO;
import indi.uhyils.pojo.cqe.event.CheckAndAddRelegationEvent;
import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.protocol.register.RelegationRegister;
import indi.uhyils.service.RelegationService;
import indi.uhyils.util.Asserts;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时07分
 */
@indi.uhyils.annotation.Register
public class RelegationRegisterImpl implements RelegationRegister {

    @Autowired
    private RelegationService service;

    @Override
    public List<Class<? extends BaseEvent>> targetEvent() {
        return Arrays.asList(
            CheckAndAddRelegationEvent.class
        );
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof CheckAndAddRelegationEvent) {
            CheckAndAddRelegationEvent checkAndAddRelegationEvent = (CheckAndAddRelegationEvent) event;
            this.checkAndAddRelegation(checkAndAddRelegationEvent);

        }
    }

    @Override
    public void checkAndAddRelegation(CheckAndAddRelegationEvent event) {
        TraceDetailDTO traceDetailDTO = event.getTraceDetailDTO();
        if (traceDetailDTO != null) {
            Integer type = traceDetailDTO.getType();
            LogTypeEnum parse = LogTypeEnum.parse(type).orElseThrow(() -> Asserts.makeException("降级监听: 未找到日志链路类型"));
            if (parse != LogTypeEnum.RPC) {
                return;
            }
        }

        service.checkAndAddRelegation(event);
    }
}
