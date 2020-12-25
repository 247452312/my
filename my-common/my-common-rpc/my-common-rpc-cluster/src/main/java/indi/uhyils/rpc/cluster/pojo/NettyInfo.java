package indi.uhyils.rpc.cluster.pojo;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 12时13分
 */
public class NettyInfo {

    /**
     * 在集群中的位置
     */
    private Integer indexInColony;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 前五次请求平均时间
     */
    private Long lastFiveSendAvgTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NettyInfo nettyInfo = (NettyInfo) o;
        return Objects.equals(indexInColony, nettyInfo.indexInColony) && Objects.equals(weight, nettyInfo.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexInColony, weight);
    }

    public Long getLastFiveSendAvgTime() {
        return lastFiveSendAvgTime;
    }

    public void setLastFiveSendAvgTime(Long lastFiveSendAvgTime) {
        this.lastFiveSendAvgTime = lastFiveSendAvgTime;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getIndexInColony() {
        return indexInColony;
    }

    public void setIndexInColony(Integer indexInColony) {
        this.indexInColony = indexInColony;
    }
}
