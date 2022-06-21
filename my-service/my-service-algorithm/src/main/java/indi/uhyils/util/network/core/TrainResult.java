package indi.uhyils.util.network.core;

/**
 * 训练结果
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 09时58分
 */
public class TrainResult {

    /**
     * 准确率
     */
    private Double accuracy;

    /**
     * 训练遍数
     */
    private Integer times;

    /**
     * 每次训练分片大小
     */
    private Integer batchSize;

    /**
     * 训练时间
     */
    private Long trainTime;

    /**
     * 模型路径
     */
    private String modelPath;

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public Long getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(Long trainTime) {
        this.trainTime = trainTime;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }
}
