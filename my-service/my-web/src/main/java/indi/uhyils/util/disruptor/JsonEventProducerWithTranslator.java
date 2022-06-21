package indi.uhyils.util.disruptor;

import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;
import indi.uhyils.util.ByteBufferUtils;
import java.nio.ByteBuffer;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月09日 07时15分
 */
public class JsonEventProducerWithTranslator {

    /**
     * 一个translator可以看做一个事件初始化器，publicEvent方法会调用它
     * 填充Event
     */
    private static final EventTranslatorTwoArg<JsonEvent, ByteBuffer, String> TRANSLATOR = (jsonEvent, l, byteBuffer, s) -> {
        jsonEvent.setValue(ByteBufferUtils.decodeKey(byteBuffer));
        jsonEvent.setToken(s);
    };

    private final RingBuffer<JsonEvent> ringBuffer;

    public JsonEventProducerWithTranslator(RingBuffer<JsonEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 发布事件
     *
     * @param json
     */
    public void onData(String json, String token) {
        ringBuffer.publishEvent(TRANSLATOR, ByteBufferUtils.encodeKey(json), token);
    }
}
