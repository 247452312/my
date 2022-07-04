package indi.uhyils.util.disruptor;

import com.lmax.disruptor.EventHandler;
import org.springframework.stereotype.Component;

/**
 * disruptor json事件消费者
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月09日 07时04分
 */
@Component
public class JsonEventConsumer implements EventHandler<JsonEvent> {

    /**
     * 发送日志
     *
     * @param jsonEvent 事件本身
     * @param l         正在处理的事件的顺序
     * @param b         是否是最后一个事件
     *
     * @throws Exception
     */
    @Override
    public void onEvent(JsonEvent jsonEvent, long l, boolean b) throws Exception {
        //        logService.pushRequestLog(ObjRequest.build(JSONObject.parseObject(jsonEvent.getValue(), LogEntity.class), jsonEvent.getToken()));
    }
}
