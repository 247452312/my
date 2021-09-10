package indi.uhyils.pojo.DTO;


/**
 * 说明书表(Instructions)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分19秒
 */
public class InstructionsDTO extends IdDTO {

    private static final long serialVersionUID = -21433580895125730L;


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
