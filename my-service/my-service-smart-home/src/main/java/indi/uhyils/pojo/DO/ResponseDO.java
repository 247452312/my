package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 设备指令回应表(Response)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时15分37秒
 */
@TableName(value = "sys_response")
public class ResponseDO extends BaseDO {

    private static final long serialVersionUID = -68151455180413955L;


    /**
     * 触发类型
     */
    @TableField
    private Integer triggerType;

    /**
     * 触发场景id
     */
    @TableField
    private Long tiggerSceneId;

    /**
     * 指令id
     */
    @TableField
    private Long instructionsId;

    /**
     * 1->人工设置
     * 2->网上批量导入
     * 3->机器自动学习
     */
    @TableField
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
