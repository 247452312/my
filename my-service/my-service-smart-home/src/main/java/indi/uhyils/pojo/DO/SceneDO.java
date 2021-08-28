package indi.uhyils.pojo.DO;

import indi.uhyils.pojo.DO.base.BaseDoDO;

/**
 * 场景表(Scene)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分28秒
 */
public class SceneDO extends BaseDoDO {

    private static final long serialVersionUID = -50383469531708028L;


    /**
     * 场景类型
     */
    private Integer type;

    /**
     * 场景数值 温度 湿度等
     */
    private Object value;

    /**
     * 其他: 气氛 天气 动作等场景的模型id
     */
    private Long otherModelId;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    public Long getOtherModelId() {
        return otherModelId;
    }

    public void setOtherModelId(Long otherModelId) {
        this.otherModelId = otherModelId;
    }

}
