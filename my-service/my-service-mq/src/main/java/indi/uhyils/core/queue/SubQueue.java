package indi.uhyils.core.queue;

import indi.uhyils.core.queue.distribute.AllPushMessageDistribute;
import indi.uhyils.core.register.Register;
import indi.uhyils.core.topic.Topic;

import java.util.concurrent.Executor;

/**
 * 分发队列
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 19时16分
 * @Version 1.0
 */
public class SubQueue extends AbstractQueue {


    public SubQueue(Topic topic, Executor executor) {
        super(topic, executor);
    }

    @Override
    protected void initDistribute() {
        this.distribute = new AllPushMessageDistribute(this);
    }


    @Override
    public Boolean tryToRegister(Register register) {
        consumer.add(register);
        return Boolean.TRUE;
    }
}
