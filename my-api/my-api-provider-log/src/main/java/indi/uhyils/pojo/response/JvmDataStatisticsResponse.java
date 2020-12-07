package indi.uhyils.pojo.response;

import indi.uhyils.enum_.ServiceQualityEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JVM数据统计信息
 * 通过一系列的算法计算出服务的运行质量,其中服务运行质量{@link JvmDataStatisticsResponse#serviceOperationQuality}代表了总体运行质量有没有好坏之分
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 06时59分
 */
public class JvmDataStatisticsResponse implements Serializable {

    /**
     * 服务在线数量
     */
    private Integer serviceOnlineCount;

    /**
     * 服务运行质量
     */
    private Boolean serviceOperationQuality;

    /**
     * 服务运行质量详情
     */
    private HashMap<Long, List<ServiceQualityEnum>> serviceMap;
    /**
     * 前台请求次数
     */
    private Integer webRequestCount;

    /**
     * 接口调用次数
     */
    private Integer interfaceCellCount;

    public static JvmDataStatisticsResponse build(Integer serviceOnlineCount, HashMap<Long, List<ServiceQualityEnum>> serviceMap, Integer webRequestCount, Integer interfaceCellCount) {
        JvmDataStatisticsResponse jvmDataStatisticsResponse = new JvmDataStatisticsResponse();
        jvmDataStatisticsResponse.setServiceOnlineCount(serviceOnlineCount);
        jvmDataStatisticsResponse.setServiceMap(serviceMap);
        jvmDataStatisticsResponse.setWebRequestCount(webRequestCount);
        jvmDataStatisticsResponse.setInterfaceCellCount(interfaceCellCount);
        // 默认是好的
        boolean serviceOperationQuality = true;
        for (Map.Entry<Long, List<ServiceQualityEnum>> entity : serviceMap.entrySet()) {
            List<ServiceQualityEnum> value = entity.getValue();
            if (value.size() != 1 || !value.get(0).equals(ServiceQualityEnum.GOOD)) {
                serviceOperationQuality = false;
                break;
            }
        }
        jvmDataStatisticsResponse.setServiceOperationQuality(serviceOperationQuality);
        return jvmDataStatisticsResponse;
    }

    public Integer getServiceOnlineCount() {
        return serviceOnlineCount;
    }

    public void setServiceOnlineCount(Integer serviceOnlineCount) {
        this.serviceOnlineCount = serviceOnlineCount;
    }

    public Boolean getServiceOperationQuality() {
        return serviceOperationQuality;
    }

    public void setServiceOperationQuality(Boolean serviceOperationQuality) {
        this.serviceOperationQuality = serviceOperationQuality;
    }

    public Integer getWebRequestCount() {
        return webRequestCount;
    }

    public void setWebRequestCount(Integer webRequestCount) {
        this.webRequestCount = webRequestCount;
    }

    public HashMap<Long, List<ServiceQualityEnum>> getServiceMap() {
        return serviceMap;
    }

    public void setServiceMap(HashMap<Long, List<ServiceQualityEnum>> serviceMap) {
        this.serviceMap = serviceMap;
    }

    public Integer getInterfaceCellCount() {
        return interfaceCellCount;
    }

    public void setInterfaceCellCount(Integer interfaceCellCount) {
        this.interfaceCellCount = interfaceCellCount;
    }
}
