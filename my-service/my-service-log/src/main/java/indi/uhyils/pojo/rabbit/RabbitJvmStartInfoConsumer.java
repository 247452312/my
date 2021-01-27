package indi.uhyils.pojo.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.dao.MonitorDao;
import indi.uhyils.dao.MonitorJvmStatusDetailDao;
import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.pojo.mqinfo.JvmStartInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmStatusInfo;
import indi.uhyils.pojo.model.LogMonitorEntity;
import indi.uhyils.pojo.model.LogMonitorJvmStatusEntity;
import indi.uhyils.util.DefaultRequestBuildUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.ModelTransUtils;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 11时33分
 */
public class RabbitJvmStartInfoConsumer extends DefaultConsumer {

    private MonitorDao monitorDao;

    private MonitorJvmStatusDetailDao monitorJvmStatusDetailDao;


    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel            the channel to which this consumer is attached
     * @param applicationContext
     */
    public RabbitJvmStartInfoConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
        monitorDao = applicationContext.getBean(MonitorDao.class);
        monitorJvmStatusDetailDao = applicationContext.getBean(MonitorJvmStatusDetailDao.class);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String text = new String(body, StandardCharsets.UTF_8);
        JvmStartInfo jvmStartInfo = JSONObject.parseObject(text, JvmStartInfo.class);
        Integer i = monitorDao.checkMonitorRepeat(jvmStartInfo.getJvmUniqueMark());
        if (i == 0) {
            /*查询有没有同样ip 且同样服务名称的,如果有,将endtime设置为现在,表示发现停止的时间*/
            monitorDao.updateMonitorThatRepeatByIpAndName(jvmStartInfo.getJvmUniqueMark().getServiceName(), jvmStartInfo.getJvmUniqueMark().getIp(), System.currentTimeMillis());

            /* 新增JVM启动信息 */
            LogUtil.info(this, "接收到JVM启动信息");
            LogUtil.info(this, text);
            LogMonitorEntity logMonitorEntity = ModelTransUtils.transJvmStartInfoToMonitorDO(jvmStartInfo);
            try {
                logMonitorEntity.preInsert(DefaultRequestBuildUtil.getAdminDefaultRequest());
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
            monitorDao.insert(logMonitorEntity);
            List<JvmStatusInfo> jvmStatusInfos = jvmStartInfo.getJvmStatusInfos();
            List<LogMonitorJvmStatusEntity> logMonitorJvmStatusEntities = ModelTransUtils.transJvmStatusInfosToMonitorJvmStatusDetailDOs(jvmStatusInfos, logMonitorEntity.getId());
            final Long[] endTime = {0L};
            logMonitorJvmStatusEntities.forEach(t -> {
                try {
                    t.preInsert(DefaultRequestBuildUtil.getAdminDefaultRequest());
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }
                monitorJvmStatusDetailDao.insert(t);
                Long time = t.getTime();
                if (time > endTime[0]) {
                    endTime[0] = time;
                }
            });
            // 修改结束时间为假想时间
            Double v = endTime[0] + RabbitMqContent.OUT_TIME * 60 * 1000 * RabbitMqContent.OUT_TIME_PRO;
            monitorDao.changeEndTime(logMonitorEntity.getId(), v.longValue());
        }
        // 获取tag(队列中的唯一标示)
        long deliveryTag = envelope.getDeliveryTag();
        // 确认 false为不批量确认
        getChannel().basicAck(deliveryTag, false);
    }
}
