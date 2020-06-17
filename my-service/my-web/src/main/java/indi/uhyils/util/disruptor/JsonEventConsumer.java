package indi.uhyils.util.disruptor;

import org.apache.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.lmax.disruptor.EventHandler;
import indi.uhyils.pojo.model.LogEntity;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.service.LogService;
import org.springframework.stereotype.Component;

/**
 * disruptor json事件消费者
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月09日 07时04分
 */
@Component
public class JsonEventConsumer implements EventHandler<JsonEvent> {
    @Reference(group = "${spring.profiles.active}", check = false)
    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    /**
     * 发送日志
     *
     * @param jsonEvent 事件本身
     * @param l         正在处理的事件的顺序
     * @param b         是否是最后一个事件
     * @throws Exception
     */
    @Override
    public void onEvent(JsonEvent jsonEvent, long l, boolean b) throws Exception {
        logService.pushRequestLog(ObjRequest.build(JSONObject.parseObject(jsonEvent.getValue(), LogEntity.class), jsonEvent.getToken()));
    }
}
