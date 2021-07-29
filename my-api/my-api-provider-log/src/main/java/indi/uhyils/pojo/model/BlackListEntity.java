package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoEntity;

/**
 * (BlackList)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月24日 06时40分36秒
 */
public class BlackListEntity extends BaseDoEntity {

    private static final long serialVersionUID = -24783323375205414L;

    /**
     * 用户ip
     */
    private String ip;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
