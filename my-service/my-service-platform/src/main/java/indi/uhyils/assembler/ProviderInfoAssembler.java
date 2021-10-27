package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.ProviderInfoDO;
import indi.uhyils.pojo.DTO.ProviderInfoDTO;
import indi.uhyils.pojo.cqe.command.RegisterProviderCommand;
import indi.uhyils.pojo.entity.ProviderInfo;
import org.mapstruct.Mapper;

/**
 * 服务提供者表(ProviderInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@Mapper(componentModel = "spring")
public abstract class ProviderInfoAssembler extends AbstractAssembler<ProviderInfoDO, ProviderInfo, ProviderInfoDTO> {

    /**
     * 注册请求转entity
     *
     * @param command
     *
     * @return
     */
    public ProviderInfo toEntity(RegisterProviderCommand command) {
        ProviderInfoDO providerInfoDO = toDo(command);
        return new ProviderInfo(providerInfoDO);
    }

    public abstract ProviderInfoDO toDo(RegisterProviderCommand command);
}

