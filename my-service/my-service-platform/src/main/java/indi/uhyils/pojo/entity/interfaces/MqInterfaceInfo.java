package indi.uhyils.pojo.entity.interfaces;

import com.alibaba.fastjson.JSON;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ConsumerFilterRepository;
import indi.uhyils.repository.InterfaceInfoRepository;
import indi.uhyils.util.Asserts;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月22日 14时35分
 */
public class MqInterfaceInfo extends InterfaceInfo implements HttpInterfaceInfoInterface {


    public MqInterfaceInfo(InterfaceInfoDO data) {
        super(data);
    }

    public MqInterfaceInfo(Long id) {
        super(id);
    }

    public MqInterfaceInfo(Long id, InterfaceInfoRepository rep) {
        super(id, rep);
    }

    public MqInterfaceInfo(Identifier id) {
        super(id);
    }

    @Override
    public JSON invoke(Long consumerId, Map<String, Object> map, ConsumerFilterRepository consumerFilterRepository) {
        InterfaceInfoDO interfaceInfoDO = toData();
        Asserts.assertTrue(interfaceInfoDO != null);
        // todo 对接平台 MQ调用
        return null;
    }
}
