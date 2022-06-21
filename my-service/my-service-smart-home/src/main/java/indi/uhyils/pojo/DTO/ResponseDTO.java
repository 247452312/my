package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 设备指令回应表(Response)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分30秒
 */
public class ResponseDTO extends IdDTO {

    private static final long serialVersionUID = -58929636345058307L;


    /**
     * 触发类型
     */
    private Integer triggerType;

    /**
     * 触发场景id
     */
    private Long tiggerSceneId;

    /**
     * 指令id
     */
    private Long instructionsId;

    /**
     * 1->人工设置
     * 2->网上批量导入
     * 3->机器自动学习
     */
    private Integer manual;


    public Integer getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(Integer triggerType) {
        this.triggerType = triggerType;
    }


    public Long getTiggerSceneId() {
        return tiggerSceneId;
    }

    public void setTiggerSceneId(Long tiggerSceneId) {
        this.tiggerSceneId = tiggerSceneId;
    }


    public Long getInstructionsId() {
        return instructionsId;
    }

    public void setInstructionsId(Long instructionsId) {
        this.instructionsId = instructionsId;
    }


    public Integer getManual() {
        return manual;
    }

    public void setManual(Integer manual) {
        this.manual = manual;
    }

}
