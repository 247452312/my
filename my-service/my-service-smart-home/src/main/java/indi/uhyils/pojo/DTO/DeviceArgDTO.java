package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 设备参数表(DeviceArg)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分18秒
 */
public class DeviceArgDTO extends IdDTO {

    private static final long serialVersionUID = -94174502752428382L;


    /**
     * 设备id
     */
    private Long deviceId;

    /**
     * 与水平夹角(下为负 上为正)
     */
    private Double directionX;

    /**
     * 与正南夹角(均为正)
     */
    private Double directionY;

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
