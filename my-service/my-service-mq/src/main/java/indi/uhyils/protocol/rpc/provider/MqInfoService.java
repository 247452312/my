package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.core.topic.Topic;
import indi.uhyils.pojo.cqe.DefaultCQE;
import java.util.ArrayList;
import java.util.List;

/**
 * MQ信息
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月30日 08时40分
 */
public interface MqInfoService {

    /**
     * 获取所有
     *
     * @return
     */
    ArrayList<Topic> getAllInfo(DefaultCQE request) throws NoSuchFieldException, IllegalAccessException;

}
