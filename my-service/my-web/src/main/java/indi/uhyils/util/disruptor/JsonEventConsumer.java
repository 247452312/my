package indi.uhyils.util.disruptor;

import com.alibaba.dubbo.config.annotation.Reference;
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
     * @param l         未知
     * @param b         未知 TODO 想办法弄明白这两个参数
     * @throws Exception
     */
    @Override
    public void onEvent(JsonEvent jsonEvent, long l, boolean b) throws Exception {
        logService.pushRequestLogNoToken(ObjRequest.build(JSONObject.parseObject(jsonEvent.getValue(), LogEntity.class), jsonEvent.getToken()));
    }
}
