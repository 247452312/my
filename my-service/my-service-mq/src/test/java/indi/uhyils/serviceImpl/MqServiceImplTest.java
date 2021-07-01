package indi.uhyils.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.TopicType;
import indi.uhyils.exception.UserException;
import indi.uhyils.pojo.request.GetMessageRequest;
import indi.uhyils.pojo.request.SendMessageRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.MqService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月18日 18时02分
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqServiceImplTest {

    private MqService service;

    @Before
    public void setUp() throws Exception {
        service = new MqServiceImpl();
    }

    @Test
    public void sendMessage() throws UserException {
        SendMessageRequest request = new SendMessageRequest();
        JSONObject data = new JSONObject();
        data.put("test1", 1);
        data.put("test2", "1");
        request.setData(data);
        request.setTopic("firstTopic");
        request.setType(TopicType.PUB_SUB);
        ServiceResult<Boolean> booleanServiceResult = service.sendMessage(request);
        Boolean data1 = booleanServiceResult.getData();
        Assert.assertTrue(data1);
        GetMessageRequest request1 = new GetMessageRequest();
        request1.setTopicName("firstTopic");

        ServiceResult<JSONObject> message = service.getMessage(request1);
        JSONObject data2 = message.getData();
        Assert.assertEquals(data2.get("test1"), 1);
        Assert.assertEquals(data2.get("test2"), "1");
        message = service.getMessage(request1);
        Assert.assertEquals(message.getData(), null);
    }

    @Test
    public void createTopic() {
    }

    @Test
    public void registerProvider() {
    }

    @Test
    public void registerConsumer() {
    }

    @Test
    public void registerPublish() {
    }

    @Test
    public void registerSubscriber() {
    }
}
