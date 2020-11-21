package indi.uhyils.mq.pojo.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbit连接创建工厂
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月18日 08时16分
 */
public class RabbitFactory extends ConnectionFactory {

    /**
     * 单例工厂
     */
    private volatile static RabbitFactory factory;

    private volatile Connection connection;

    private RabbitFactory(String host, Integer port, String username, String password) {
        setHost(host);
        setPort(port);
        setUsername(username);
        setPassword(password);
    }

    /**
     * 双重检测锁
     *
     * @return 单例
     */
    public static RabbitFactory getInstance(String host, Integer port, String username, String password) {
        if (null == factory) {
            synchronized (RabbitFactory.class) {
                if (null == factory) {
                    factory = new RabbitFactory(host, port, username, password);
                }
            }
        }
        return factory;
    }

    public Connection getConn() throws IOException, TimeoutException {
        if (null == connection) {
            synchronized (this) {
                if (null == connection) {
                    connection = newConnection();
                }
            }
        }
        return connection;
    }

}
