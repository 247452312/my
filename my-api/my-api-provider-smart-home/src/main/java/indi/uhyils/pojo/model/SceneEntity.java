package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * (Scene)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分53秒
 */
public class SceneEntity extends BaseVoEntity {

    private static final long serialVersionUID = 284367298143371524L;

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
    private String otherModelId;


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

    public String getOtherModelId() {
        return otherModelId;
    }

    public void setOtherModelId(String otherModelId) {
        this.otherModelId = otherModelId;
    }

}
