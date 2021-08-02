package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 添加黑名单
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月24日 09时32分
 */
public class AddBlackIpRequest extends DefaultRequest {

    /**
     * ip
     */
    private String ip;

    public static AddBlackIpRequest build(String ip) {
        AddBlackIpRequest build = new AddBlackIpRequest();
        build.ip = ip;
        return build;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
