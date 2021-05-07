package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;


/**
 * 默认的连接方式
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月07日 08时49分
 */
public class DefaultLinkRequest extends DefaultRequest {

    /**
     * ip
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * netty连接句柄
     */
    private String channelId;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
