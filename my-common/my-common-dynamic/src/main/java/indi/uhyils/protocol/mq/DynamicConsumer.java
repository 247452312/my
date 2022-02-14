package indi.uhyils.protocol.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import indi.uhyils.mq.pojo.mqinfo.JvmStartInfoCommand;
import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.pojo.entity.RemoteDynamicCodeEntityInterface;
import indi.uhyils.pojo.entity.impl.RemoteDynamicCodeEntityImpl;
import indi.uhyils.util.LogUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationContext;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月12日 10时37分
 */
public class DynamicConsumer extends DefaultConsumer {


    /**
     * @param channel
     * @param applicationContext
     */
    public DynamicConsumer(Channel channel, ApplicationContext applicationContext) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String text = new String(body, StandardCharsets.UTF_8);
        List<DynamicCodeDTO> dynamicCodeDTOList = JSONObject.parseArray(text, DynamicCodeDTO.class);
        List<Integer> dyIds = dynamicCodeDTOList.stream().map(DynamicCodeDTO::getGroupId).distinct().collect(Collectors.toList());
        LogUtil.info(this, "接收到动态代码编译信息:{}", JSON.toJSONString(dyIds));
        RemoteDynamicCodeEntityInterface codeEntityInterface = new RemoteDynamicCodeEntityImpl(dynamicCodeDTOList);
        codeEntityInterface.permanentDynamic(() -> null);

        // 获取tag(队列中的唯一标示)
        long deliveryTag = envelope.getDeliveryTag();
        // 确认 false为不批量确认
        getChannel().basicAck(deliveryTag, false);
    }

}
