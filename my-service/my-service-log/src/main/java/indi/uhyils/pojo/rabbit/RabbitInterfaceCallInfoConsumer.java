package indi.uhyils.pojo.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.dao.LogMonitorDao;
import indi.uhyils.dao.LogMonitorInterfaceDetailDao;
import indi.uhyils.mq.pojo.mqinfo.InterfaceCallInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.pojo.model.LogMonitorEntity;
import indi.uhyils.pojo.model.LogMonitorInterfaceCallEntity;
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

    private LogMonitorInterfaceDetailDao monitorInterfaceDetailDao;

    private LogMonitorDao monitorDao;


    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel            the channel to which this consumer is attached
     * @param applicationContext
     */
    public RabbitInterfaceCallInfoConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
        monitorInterfaceDetailDao = applicationContext.getBean(LogMonitorInterfaceDetailDao.class);
        monitorDao = applicationContext.getBean(LogMonitorDao.class);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String text = new String(body, StandardCharsets.UTF_8);
        LogUtil.info(this, "接收到接口调用信息");
        LogUtil.info(this, text);
        InterfaceCallInfo interfaceCallInfo = JSONObject.parseObject(text, InterfaceCallInfo.class);
        QueryWrapper<LogMonitorEntity> queryWrapper = new QueryWrapper<>();
        JvmUniqueMark jvmUniqueMark = interfaceCallInfo.getJvmUniqueMark();
        queryWrapper.select("id")
                .eq("service_name", jvmUniqueMark.getServiceName())
                .eq("time", jvmUniqueMark.getTime())
                .eq("ip", jvmUniqueMark.getIp());
        LogMonitorEntity logMonitorEntity = monitorDao.selectOne(queryWrapper);
        LogMonitorInterfaceCallEntity monitorInterfaceDetailDO = ModelTransUtils.transInterfaceCallInfoToMonitorInterfaceDetailDO(interfaceCallInfo, logMonitorEntity.getId());
        try {
            monitorInterfaceDetailDO.preInsert(null);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
        monitorInterfaceDetailDao.insert(monitorInterfaceDetailDO);
    }
}
