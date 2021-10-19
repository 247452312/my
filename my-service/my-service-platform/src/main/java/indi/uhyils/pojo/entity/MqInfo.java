package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.enum_.MqTypeEnum;
import indi.uhyils.pojo.DO.MqInfoDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.MqInfoRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.MQManager;

/**
 * mq连接信息表(MqInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
public class MqInfo extends SourceInfo<MqInfoDO> {

    @Default
    public MqInfo(MqInfoDO data) {
        super(data);
    }

    public MqInfo(Long id) {
        super(id, new MqInfoDO());
    }

    public MqInfo(Long id, MqInfoRepository rep) {
        super(id, new MqInfoDO());
        completion(rep);
    }

    public MqInfo(Identifier id) {
        super(id, new MqInfoDO());
    }

    @Override
    public Boolean testConnect() {
        MqInfoDO mqInfoDO = toData();
        Asserts.assertTrue(mqInfoDO != null);
        Integer type = mqInfoDO.getType();
        MqTypeEnum typeEnum = MqTypeEnum.parse(type);
        Asserts.assertTrue(typeEnum != null);

        switch (typeEnum) {
            case RABBIT_MQ:
                return MQManager.testConnectRabbitMQ(mqInfoDO.getUrl(), mqInfoDO.getPort(), mqInfoDO.getUsername(), mqInfoDO.getPassword());
            case ROCKET_MQ:
                return MQManager.testConnectRocketMQ(mqInfoDO.getUrl(), mqInfoDO.getAccessKey(), mqInfoDO.getSecretKey());
            default:
                Asserts.assertTrue(false, "暂不支持数据库类型");
                return false;
        }

    }


}
