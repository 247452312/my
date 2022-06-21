package indi.uhyils.core.queue.distribute;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.Queue;
import indi.uhyils.core.register.Register;
import java.util.Collection;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月16日 21时52分
 * @Version 1.0
 */
public class AllPushMessageDistribute extends AbstractMessageDistributeRunnable {

    public AllPushMessageDistribute(Queue queue) {
        super(queue);
    }

    @Override
    public void sendMessage(Message message, Collection<Register> registers) {
        registers.forEach(t -> t.pushMessage(message));
    }

}
