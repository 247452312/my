package indi.uhyils.netty;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 09时14分
 */
public interface MysqlNettyServer {


    /**
     * 初始化netty
     *
     * @throws InterruptedException 被打断
     */
    void init() throws InterruptedException;
}
