package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDoDO;

/**
 * (Response)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分51秒
 */
public class ResponseDO extends BaseDoDO {

    private static final long serialVersionUID = -32346207764606945L;

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
