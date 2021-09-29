package indi.uhyils.facade;

import indi.uhyils.pojo.DTO.RelegationDTO;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月28日 16时09分
 */
public interface ServiceControlFacade extends BaseFacade {

    /**
     * 降级
     *
     * @param serviceName 接口名称
     * @param methodName  方法名称
     *
     * @return
     */
    boolean demotion(String serviceName, String methodName);

    /**
     * 恢复
     *
     * @param serviceName
     * @param methodName
     *
     * @return
     */
    boolean recover(String serviceName, String methodName);

    /**
     * 填充是否降级
     *
     * @param dtos
     */
    void fillDisable(List<RelegationDTO> dtos);

    /**
     * 填充是否降级
     *
     * @param dto
     */
    void fillDisable(RelegationDTO dto);
}
