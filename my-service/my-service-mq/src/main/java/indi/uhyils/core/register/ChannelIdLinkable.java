package indi.uhyils.core.register;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月07日 08时37分
 */
public interface ChannelIdLinkable {

    /**
     * 获取netty连接句柄
     *
     * @return
     */
    String getChannelId();
}
