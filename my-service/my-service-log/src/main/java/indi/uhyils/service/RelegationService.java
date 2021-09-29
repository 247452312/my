package indi.uhyils.service;


import indi.uhyils.pojo.DTO.RelegationDTO;
import indi.uhyils.pojo.cqe.event.CheckAndAddRelegationEvent;

/**
 * 接口降级策略(Relegation)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时33分23秒
 */
public interface RelegationService extends BaseDoService<RelegationDTO> {

    /**
     * 检查并插入接口降级权重表
     *
     * @param event
     */
    void checkAndAddRelegation(CheckAndAddRelegationEvent event);

    /**
     * 降级
     *
     * @param serviceName
     * @param methodName
     *
     * @return
     */
    Boolean demotion(String serviceName, String methodName);

    /**
     * 恢复
     *
     * @param serviceName
     * @param methodName
     *
     * @return
     */
    Boolean recover(String serviceName, String methodName);
}
