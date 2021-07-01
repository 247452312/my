package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * 算法类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月27日 06时24分
 */
public class AlgorithmEntity extends BaseVoEntity {
    /**
     * 算法名称
     */
    private String name;

    /**
     * 模型存储地点
     */
    private String modelFilePath;

    /**
     * 算法准确率
     */
    private Double accuracy;

    /**
     * 输入参数
     */
    private Integer inParamSize;

    /**
     * 输出参数
     */
    private Integer outParamSize;


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
}
