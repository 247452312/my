package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.cqe.query.AbstractArgQuery;

/**
 * 获取指定ip的访问请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月23日 10时21分
 */
public class GetLogIntervalByIpQuery extends AbstractArgQuery {

    /**
     * 指定ip
     */
    private String ip;

    public static GetLogIntervalByIpQuery build(String ip) {
        GetLogIntervalByIpQuery build = new GetLogIntervalByIpQuery();
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
