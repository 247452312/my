package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.ProviderInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.RegisterProviderCommand;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * 服务提供者表(ProviderInfo)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分40秒
 */
public interface ProviderInfoProvider extends DTOProvider<ProviderInfoDTO> {

    /**
     * 注册生产者
     *
     * @param command
     *
     * @return
     */
    ServiceResult<ProviderInfoDTO> registerProvider(RegisterProviderCommand command);
}

