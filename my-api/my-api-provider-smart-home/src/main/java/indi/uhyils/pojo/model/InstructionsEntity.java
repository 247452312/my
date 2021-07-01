package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * (Instructions)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分50秒
 */
public class InstructionsEntity extends BaseVoEntity {

    private static final long serialVersionUID = -90971541994755037L;


    /**
     * 被动方设备id
     */
    private Long passiveDeviceId;

    /**
     * 传递中介(路由器 or 红外线发射器)
     */
    private String transmissionIntermediary;

    /**
     * 指令代码(与程序中的enum相对应)
     */
    private Integer instructionsTypeCode;

    /**
     * 指令描述
     */
    private String instructionsDescribe;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPassiveDeviceId() {
        return passiveDeviceId;
    }

    public void setPassiveDeviceId(Long passiveDeviceId) {
        this.passiveDeviceId = passiveDeviceId;
    }

    public String getTransmissionIntermediary() {
        return transmissionIntermediary;
    }

    public void setTransmissionIntermediary(String transmissionIntermediary) {
        this.transmissionIntermediary = transmissionIntermediary;
    }

    public Integer getInstructionsTypeCode() {
        return instructionsTypeCode;
    }

    public void setInstructionsTypeCode(Integer instructionsTypeCode) {
        this.instructionsTypeCode = instructionsTypeCode;
    }

    public String getInstructionsDescribe() {
        return instructionsDescribe;
    }

    public void setInstructionsDescribe(String instructionsDescribe) {
        this.instructionsDescribe = instructionsDescribe;
    }

}
