package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ProviderInterfaceParamAssembler;
import indi.uhyils.pojo.DO.ProviderInterfaceParamDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceParamDTO;
import indi.uhyils.pojo.entity.ProviderInterfaceParam;
import indi.uhyils.repository.ProviderInterfaceParamRepository;
import indi.uhyils.service.ProviderInterfaceParamService;
import org.springframework.stereotype.Service;

/**
* 接口参数表(ProviderInterfaceParam)表 内部服务实现类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年08月12日 08时33分
*/
@Service
@ReadWriteMark(tables = {"sys_provider_interface_param"})
public class ProviderInterfaceParamServiceImpl extends AbstractDoService<ProviderInterfaceParamDO, ProviderInterfaceParam, ProviderInterfaceParamDTO, ProviderInterfaceParamRepository, ProviderInterfaceParamAssembler> implements ProviderInterfaceParamService {

    public ProviderInterfaceParamServiceImpl(ProviderInterfaceParamAssembler assembler, ProviderInterfaceParamRepository repository) {
        super(assembler, repository);
    }


}
