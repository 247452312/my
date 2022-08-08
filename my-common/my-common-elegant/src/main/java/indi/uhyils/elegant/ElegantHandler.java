package indi.uhyils.elegant;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月02日 08时37分
 */
public interface ElegantHandler {

    /**
     * 判断组件是否启动成功
     *
     * @return
     */
    Boolean isOnline();

    /**
     * 允许组件对外发布服务的通知接口
     */
    void allowToPublish();

    /**
     * 不允许组件对外发布服务的通知接口
     */
    void notAllowToPublish();

    /**
     * 通知组件要关闭了(并不是真的关闭,只是通知组件做好关闭的准备)
     */
    void shutdown();

    /**
     * 组件是否正在忙碌
     *
     * @return
     */
    Boolean isBusy();


    /**
     * 强制关闭组件
     */
    void close();

    /**
     * 此优雅上下线组件的名称
     *
     * @return
     */
    String name();
}
