package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 设备参数表(DeviceArg)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时15分37秒
 */
@TableName(value = "sys_device_arg")
public class DeviceArgDO extends BaseDO {

    private static final long serialVersionUID = -52694045146310252L;


    /**
     * 设备id
     */
    @TableField
    private Long deviceId;

    /**
     * 与水平夹角(下为负 上为正)
     */
    @TableField
    private Double directionX;

    /**
     * 与正南夹角(均为正)
     */
    @TableField
    private Double directionY;

    /**
     * 是否可动
     */
    @TableField
    private Integer canMove;

    /**
     * 是否可以转动
     */
    @TableField
    private Integer canRotate;


    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }


    public Double getDirectionX() {
        return directionX;
    }

    public void setDirectionX(Double directionX) {
        this.directionX = directionX;
    }


    public Double getDirectionY() {
        return directionY;
    }

    public void setDirectionY(Double directionY) {
        this.directionY = directionY;
    }


    public Integer getCanMove() {
        return canMove;
    }

    public void setCanMove(Integer canMove) {
        this.canMove = canMove;
    }


    public Integer getCanRotate() {
        return canRotate;
    }

    public void setCanRotate(Integer canRotate) {
        this.canRotate = canRotate;
    }

}
