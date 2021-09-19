package indi.uhyils.pojo.DTO;


import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 算法表(Algorithm)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 20时58分09秒
 */
public class AlgorithmDTO extends IdDTO {

    private static final long serialVersionUID = 839502688584316497L;


    /**
     * 算法名称
     */
    private String name;

    /**
     * 模型所在地
     */
    private String modelFilePath;

    /**
     * 准确率
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

    /**
     * 算法类型(1->深度学习,2->神经网络)
     */
    private Integer type;

    /**
     * 算法结构,load时用
     */
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
