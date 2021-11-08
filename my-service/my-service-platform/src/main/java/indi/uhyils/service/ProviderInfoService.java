package indi.uhyils.service;


import indi.uhyils.pojo.DTO.ProviderInfoDTO;
import indi.uhyils.pojo.cqe.command.RegisterProviderCommand;

/**
 * 服务提供者表(ProviderInfo)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
public interface ProviderInfoService extends BaseDoService<ProviderInfoDTO> {

    /**
     * 注册生产者
     *
     * @param command
     *
     * @return
     */
    ProviderInfoDTO registerProvider(RegisterProviderCommand command);
}
