package indi.uhyils.core.queue.distribute;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.queue.Queue;
import indi.uhyils.core.register.Register;
import java.util.Collection;
import java.util.Optional;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月16日 21时52分
 * @Version 1.0
 */
public class OneMessageDistribute extends AbstractMessageDistributeRunnable {

    public OneMessageDistribute(Queue queue) {
        super(queue);
    }

    @Override
    public void sendMessage(Message message, Collection<Register> registers) {
        Optional<Register> any = registers.stream().findAny();
        if (any.isPresent()) {
            Register register = any.get();
            register.pushMessage(message);
        }
    }

}
