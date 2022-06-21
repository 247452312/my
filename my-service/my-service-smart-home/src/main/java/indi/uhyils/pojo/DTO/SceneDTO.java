package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 场景表(Scene)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分33秒
 */
public class SceneDTO extends IdDTO {

    private static final long serialVersionUID = -31110117594400156L;


    /**
     * 场景类型
     */
    private Integer type;

    /**
     * 场景数值 温度 湿度等
     */
    private Double value;

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


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


    public Long getOtherModelId() {
        return otherModelId;
    }

    public void setOtherModelId(Long otherModelId) {
        this.otherModelId = otherModelId;
    }

}
