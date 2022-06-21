package indi.uhyils.register;

import indi.uhyils.pojo.cqe.FlushAllSysEvent;
import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.protocol.register.base.Register;
import indi.uhyils.service.ParamService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月17日 16时28分
 */
@indi.uhyils.annotation.Register
public class FlushSysRegister implements Register {

    @Autowired
    private ParamService service;

    @Override
    public List<Class<? extends BaseEvent>> targetEvent() {
        return Arrays.asList(FlushAllSysEvent.class);
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (event instanceof FlushAllSysEvent) {
            service.flushAllGlobal();
        }
    }
}
