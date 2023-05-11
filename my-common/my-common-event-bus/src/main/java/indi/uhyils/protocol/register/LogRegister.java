package indi.uhyils.protocol.register;

import indi.uhyils.pojo.cqe.event.base.BaseEvent;
import indi.uhyils.protocol.register.base.Register;
import indi.uhyils.util.LogUtil;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月22日 14时39分
 */
@indi.uhyils.annotation.Register
public class LogRegister implements Register {

    @Value("${event.log.info:true}")
    private Boolean info;

    @Override
    public List<Class<? extends BaseEvent>> targetEvent() {
        return Arrays.asList(BaseEvent.class);
    }

    @Override
    public void onEvent(BaseEvent event) {
        if (info) {
            LogUtil.info(MessageFormat.format("事件:{0}发布", event.getClass()));
        } else {
            if (LogUtil.isDebugEnabled(this)) {
                LogUtil.debug(MessageFormat.format("事件:{0}发布", event.getClass()));
            }
        }
    }

}
