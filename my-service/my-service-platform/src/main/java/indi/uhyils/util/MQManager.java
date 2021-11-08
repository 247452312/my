package indi.uhyils.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月19日 08时33分
 */
public final class MQManager {


    /**
     * 尝试连接rabbitmq
     *
     * @param url
     * @param port
     * @param username
     * @param password
     *
     * @return
     */
    public static Boolean testConnectRabbitMQ(String url, Integer port, String username, String password) {
        try {
            Connection connection;
            //定义一个连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            //设置服务端地址（域名地址/ip）
            factory.setHost(url);
            //设置服务器端口号
            factory.setPort(port);
            //设置虚拟主机(相当于数据库中的库)
            factory.setVirtualHost("/");
            //设置用户名
            factory.setUsername(username);
            //设置密码
            factory.setPassword(password);
            connection = factory.newConnection();
            connection.close();
            return true;
        } catch (Exception e) {
            LogUtil.error(e, "连接rabbitMQ失败,url:{}", url);
            return false;
        }
    }

    /**
     * 尝试连接rocketMQ
     *
     * @param url
     * @param accessKey
     * @param secretKey
     *
     * @return
     */
    public static Boolean testConnectRocketMQ(String url, String accessKey, String secretKey) {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
            producer.setNamesrvAddr(url);
            producer.setVipChannelEnabled(false);
            producer.start();
            return true;
        } catch (MQClientException e) {
            LogUtil.error(e, "连接rocketMQ失败,url:{}", url);
            return false;
        }
    }
}
