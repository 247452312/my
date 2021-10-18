package indi.uhyils.pojo.entity;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.MqInfoDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.MqInfoRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;

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
        // todo 数据库差的有点多啊
        try {
            Connection connection = null;
            //定义一个连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            //设置服务端地址（域名地址/ip）
            factory.setHost(mqInfoDO.getUrl());
            //设置服务器端口号
            factory.setPort(5672);
            //设置虚拟主机(相当于数据库中的库)
            factory.setVirtualHost("/");
            //设置用户名
            factory.setUsername("");
            //设置密码
            factory.setPassword("888888");
            connection = factory.newConnection();
            connection.close();
            return true;
        } catch (Exception e) {
            LogUtil.error(e, "mq连接失败,url:{}", mqInfoDO.getUrl());
            return false;
        }
    }


}
