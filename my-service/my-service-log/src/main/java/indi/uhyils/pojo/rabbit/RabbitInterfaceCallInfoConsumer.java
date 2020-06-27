package indi.uhyils.pojo.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.dao.MonitorDao;
import indi.uhyils.dao.MonitorInterfaceDetailDao;
import indi.uhyils.pojo.model.MonitorInterfaceDetailDO;
import indi.uhyils.pojo.mqinfo.InterfaceCallInfo;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.ModelTransUtils;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 11时33分
 */
public class RabbitInterfaceCallInfoConsumer extends DefaultConsumer {

    private MonitorInterfaceDetailDao monitorInterfaceDetailDao;

    private MonitorDao monitorDao;


    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel            the channel to which this consumer is attached
     * @param applicationContext
     */
    public RabbitInterfaceCallInfoConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
        monitorInterfaceDetailDao = applicationContext.getBean(MonitorInterfaceDetailDao.class);
        monitorDao = applicationContext.getBean(MonitorDao.class);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String text = new String(body, StandardCharsets.UTF_8);
        LogUtil.info(this, "接收到接口调用信息");
        LogUtil.info(this, text);
        InterfaceCallInfo interfaceCallInfo = JSONObject.parseObject(text, InterfaceCallInfo.class);
        String id = monitorDao.getIdByJvmUniqueMark(interfaceCallInfo.getJvmUniqueMark());
        MonitorInterfaceDetailDO monitorInterfaceDetailDO = ModelTransUtils.transInterfaceCallInfoToMonitorInterfaceDetailDO(interfaceCallInfo, id);
        monitorInterfaceDetailDO.preInsert(null);
        monitorInterfaceDetailDao.insert(monitorInterfaceDetailDO);
    }
}
