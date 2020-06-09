package indi.uhyils.util.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月09日 07时21分
 */
public class DisruptorTest {
    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        // Executor that will be used to construct new threads for consumers
        ThreadPoolExecutor jsonExecutor = new ThreadPoolExecutor(1, 3, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
        // The factory for the event
        JsonEventFactory factory = new JsonEventFactory();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        // Construct the Disruptor
        Disruptor<JsonEvent> disruptor = new Disruptor<JsonEvent>(factory, bufferSize, jsonExecutor);
        // Connect the handler
        disruptor.handleEventsWith(new JsonEventConsumer());
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<JsonEvent> ringBuffer = disruptor.getRingBuffer();

        JsonEventProducerWithTranslator producer = new JsonEventProducerWithTranslator(ringBuffer);
//        for (int l = 0; l < 10; l++) {
//            String s = "{\"status\":true}";
//            producer.onData(s);
//            Thread.sleep(1000);
//        }


    }
}
