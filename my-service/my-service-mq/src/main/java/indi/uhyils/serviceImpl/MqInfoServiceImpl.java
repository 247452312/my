package indi.uhyils.serviceImpl;

import indi.uhyils.core.topic.Topic;
import indi.uhyils.core.topic.TopicFactory;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.provider.MqInfoService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月30日 08时45分
 */
@Service
public class MqInfoServiceImpl implements MqInfoService {
    @Override
    public ServiceResult<ArrayList<Topic>> getAllInfo(DefaultRequest request) throws NoSuchFieldException, IllegalAccessException {
        Field topicMapField = TopicFactory.class.getDeclaredField("topicMap");
        topicMapField.setAccessible(true);
        Map<String, Topic> topicMap = (Map<String, Topic>) topicMapField.get(TopicFactory.class);
        return ServiceResult.buildSuccessResult(new ArrayList<>(topicMap.values()));
    }
}
