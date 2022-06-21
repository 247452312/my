package indi.uhyils.pojo.DTO.request;


import indi.uhyils.pojo.cqe.DefaultCQE;

/**
 * 默认的连接方式
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月07日 08时49分
 */
public class DefaultLinkRequest extends DefaultCQE {

    /**
     * url
     */
    private String url;


    /**
     * netty连接句柄
     */
    private String channelId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
