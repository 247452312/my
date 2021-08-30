package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 设备参数表(DeviceArg)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分12秒
 */
public class DeviceArgDO extends BaseDO {

    private static final long serialVersionUID = -28368666303964952L;


    /**
     * 设备id
     */
    private Long deviceId;

    /**
     * 与水平夹角(下为负 上为正)
     */
    private Object directionX;

    /**
     * 与正南夹角(均为正)
     */
    private Object directionY;

    /**
     * 是否可动
     */
    private Integer canMove;

    /**
     * 是否可以转动
     */
    private Integer canRotate;


    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }


    public Object getDirectionX() {
        return directionX;
    }

    public void setDirectionX(Object directionX) {
        this.directionX = directionX;
    }


    public Object getDirectionY() {
        return directionY;
    }

    public void setDirectionY(Object directionY) {
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
