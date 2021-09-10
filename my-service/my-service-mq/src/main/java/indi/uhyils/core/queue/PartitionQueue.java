package indi.uhyils.core.queue;

import indi.uhyils.core.queue.distribute.OneMessageDistribute;
import indi.uhyils.core.register.Register;
import indi.uhyils.core.register.logic.LogicEngineHelper;
import indi.uhyils.core.topic.Topic;
import indi.uhyils.exception.ExpressionInvalidException;
import java.util.concurrent.Executor;

/**
 * 分区队列
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 19时16分
 * @Version 1.0
 */
public class PartitionQueue extends AbstractQueue {

    /**
     * 此队列对应的分区值
     */
    private final Object key;

    public PartitionQueue(Topic topic, Executor executor, Object key) {
        super(topic, executor);
        this.key = key;
    }

    @Override
    protected void initDistribute() {
        this.distribute = new OneMessageDistribute(this);
    }

    @Override
    public Boolean tryToRegister(Register register) throws ExpressionInvalidException {
        String expression = register.getExpression();
        boolean check = LogicEngineHelper.check(expression, key);
        if (check) {
            consumer.add(register);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
