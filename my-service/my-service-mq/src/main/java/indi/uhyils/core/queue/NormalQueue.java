package indi.uhyils.core.queue;

import indi.uhyils.core.queue.distribute.OneMessageDistribute;
import indi.uhyils.core.register.Register;
import indi.uhyils.core.topic.Topic;

import java.util.concurrent.Executor;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 19时16分
 * @Version 1.0
 */
public class NormalQueue extends AbstractQueue {

    public NormalQueue(Topic topic, Executor executor) {
        super(topic, executor);
    }

    @Override
    protected void initDistribute() {
        this.distribute = new OneMessageDistribute(this);
    }

    @Override
    public Boolean tryToRegister(Register register) {
        consumer.add(register);
        return true;
    }
}
