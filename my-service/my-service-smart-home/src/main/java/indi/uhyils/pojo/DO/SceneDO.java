package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 场景表(Scene)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时15分38秒
 */
@TableName(value = "sys_scene")
public class SceneDO extends BaseDO {

    private static final long serialVersionUID = 461074313333171420L;


    /**
     * 场景类型
     */
    @TableField
    private Integer type;

    /**
     * 场景数值 温度 湿度等
     */
    @TableField
    private Double value;

    /**
     * 其他: 气氛 天气 动作等场景的模型id
     */
    @TableField
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
