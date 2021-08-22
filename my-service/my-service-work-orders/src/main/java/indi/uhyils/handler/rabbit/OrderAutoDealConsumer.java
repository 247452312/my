package indi.uhyils.handler.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.dao.OrderApiDao;
import indi.uhyils.handler.InitApiHandler;
import indi.uhyils.handler.RunApiHandler;
import indi.uhyils.handler.SaveApiHandler;
import indi.uhyils.handler.TransApiHandler;
import indi.uhyils.pojo.model.OrderApiDO;
import indi.uhyils.pojo.model.OrderNodeDO;
import indi.uhyils.pojo.temp.InitApiRequestTemporary;
import indi.uhyils.pojo.temp.InitToRunApiTemporary;
import indi.uhyils.pojo.temp.RunToSaveApiTemporary;
import indi.uhyils.pojo.temp.SaveToTransApiTemporary;
import indi.uhyils.util.ObjectByteUtil;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * 工单自动节点处理方式
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月24日 07时27分
 */
public class OrderAutoDealConsumer extends DefaultConsumer {


    private ApplicationContext applicationContext;

    /*以下两个 初始化方法在构造方法中*/

    private OrderApiDao orderApiDao;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public OrderAutoDealConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
        this.applicationContext = applicationContext;
        orderApiDao = applicationContext.getBean(OrderApiDao.class);

    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        InitApiRequestTemporary initApiRequestTemporary = ObjectByteUtil.toObject(body, InitApiRequestTemporary.class);
        OrderNodeDO orderNodeEntity = initApiRequestTemporary.getOrderNode();

        // 初始化方法
        OrderApiDO initApiEntity = orderApiDao.getById(orderNodeEntity.getInitApiId());
        InitApiHandler initHandler = applicationContext.getBean(initApiEntity.getBeanName(), InitApiHandler.class);
        InitToRunApiTemporary init = initHandler.init(initApiRequestTemporary);

        // 运行方法
        OrderApiDO runApiEntity = orderApiDao.getById(orderNodeEntity.getRunApiId());
        RunApiHandler runHandler = applicationContext.getBean(runApiEntity.getBeanName(), RunApiHandler.class);
        RunToSaveApiTemporary run = runHandler.run(init);

        // 保存方法
        OrderApiDO saveApiEntity = orderApiDao.getById(orderNodeEntity.getSaveApiId());
        SaveApiHandler saveHandler = applicationContext.getBean(saveApiEntity.getBeanName(), SaveApiHandler.class);
        SaveToTransApiTemporary save = saveHandler.save(run);

        // 运行方法
        OrderApiDO transApiEntity = orderApiDao.getById(orderNodeEntity.getTransApiId());
        TransApiHandler transHandler = applicationContext.getBean(transApiEntity.getBeanName(), TransApiHandler.class);
        transHandler.trans(save);

    }
}
