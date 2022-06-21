package indi.uhyils.core.queue;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.register.Register;
import indi.uhyils.exception.ExpressionInvalidException;
import java.util.List;

/**
 * 队列
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 16时22分
 * @Version 1.0
 */
public interface Queue extends TopicObserver {

    /**
     * 默认队列名称
     */
    String DEFAULT_QUEUE = "defaultQueue";

    /**
     * 获取一个,如果是普通消息,则获取第一个 如果是全局顺序消息,则通知topic 获取全局第一个, 如果是分区顺序消息,则通知topic获取此区第一个
     * 阻塞方法
     *
     * @return
     */
    Message takeOne();

    /**
     * 获取一个,如果是普通消息,则获取第一个 如果是全局顺序消息,则通知topic 获取全局第一个, 如果是分区顺序消息,则通知topic获取此区第一个
     * 非阻塞方法
     *
     * @return
     */
    Message getOne() throws InterruptedException;

    /**
     * 获取n个
     *
     * @param count
     *
     * @return
     */
    Message[] getMany(Integer count);

    /**
     * 获取此队列中的最大序列(最晚的一个)
     *
     * @return
     */
    Long getMaxSequence();

    /**
     * 获取此队列中最短序列(最早的一个)
     *
     * @return
     */
    Long getMinSequence();

    /**
     * 保存信息
     *
     * @param message
     *
     * @return
     */
    Boolean saveMessage(Message message);

    /**
     * 获取队列中有多少个
     *
     * @return
     */
    int size();


    /**
     * 尝试将注册者注册到此队列
     *
     * @param register
     *
     * @return 是否可以注册
     */
    Boolean tryToRegister(Register register) throws ExpressionInvalidException;

    /**
     * 获取所有的注册到此队列上的consumer
     *
     * @return
     */
    List<Register> getConsumer();
}
