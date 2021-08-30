package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 设备表(Device)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分08秒
 */
public class DeviceDO extends BaseDO {

    private static final long serialVersionUID = 621359767799470838L;


    /**
     * 设备名称
     */
    private String name;

    /**
     * 空间表id(代表此空间的定位)
     */
    private Long spaceId;

    /**
     * 设备类型 1->观察设备 2->接受设备 3->观察接受设备
     */
    private Integer type;

    /**
     * 设备在局域网中的ip
     */
    private String ip;

    /**
     * 设备接收指令端口
     */
    private Integer port;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


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

}
