package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 设备表(Device)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时15分37秒
 */
@TableName(value = "sys_device")
public class DeviceDO extends BaseDO {

    private static final long serialVersionUID = -79622439970637774L;


    /**
     * 设备名称
     */
    @TableField
    private String name;

    /**
     * 空间表id(代表此空间的定位)
     */
    @TableField
    private Long spaceId;

    /**
     * 设备类型 1->观察设备 2->接受设备 3->观察接受设备
     */
    @TableField
    private Integer type;

    /**
     * 设备在局域网中的ip
     */
    @TableField
    private String ip;

    /**
     * 设备接收指令端口
     */
    @TableField
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
