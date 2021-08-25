package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDoDO;

/**
 * (DeviceArg)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分42秒
 */
public class DeviceArgDO extends BaseDoDO {

    private static final long serialVersionUID = 644759608272208151L;

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
    private Object canMove;

    /**
     * 是否可以转动
     */
    private Object canRotate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Object getCanMove() {
        return canMove;
    }

    public void setCanMove(Object canMove) {
        this.canMove = canMove;
    }

    public Object getCanRotate() {
        return canRotate;
    }

    public void setCanRotate(Object canRotate) {
        this.canRotate = canRotate;
    }

}
