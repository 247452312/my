package indi.uhyils.util.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Disruptor 生产json事件的工厂
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月09日 07时02分
 */
public class JsonEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new JsonEvent();
    }
}
