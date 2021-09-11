package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 算法表(Algorithm)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时51分25秒
 */
@TableName(value = "sys_algorithm")
public class AlgorithmDO extends BaseDO {

    private static final long serialVersionUID = -49241463737845804L;


    /**
     * 算法名称
     */
    @TableField
    private String name;

    /**
     * 模型所在地
     */
    @TableField
    private String modelFilePath;

    /**
     * 准确率
     */
    @TableField
    private Double accuracy;

    /**
     * 输入参数
     */
    @TableField
    private Integer inParamSize;

    /**
     * 输出参数
     */
    @TableField
    private Integer outParamSize;

    /**
     * 算法类型(1->深度学习,2->神经网络)
     */
    @TableField
    private Integer type;

    /**
     * 算法结构,load时用
     */
    @TableField
    private String structure;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getModelFilePath() {
        return modelFilePath;
    }

    public void setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
    }


    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }


    public Integer getInParamSize() {
        return inParamSize;
    }

    public void setInParamSize(Integer inParamSize) {
        this.inParamSize = inParamSize;
    }


    public Integer getOutParamSize() {
        return outParamSize;
    }

    public void setOutParamSize(Integer outParamSize) {
        this.outParamSize = outParamSize;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

}
