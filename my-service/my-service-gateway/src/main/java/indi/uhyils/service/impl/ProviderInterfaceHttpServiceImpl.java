package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ProviderInterfaceHttpAssembler;
import indi.uhyils.pojo.DO.ProviderInterfaceHttpDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceHttpDTO;
import indi.uhyils.pojo.entity.ProviderInterfaceHttp;
import indi.uhyils.repository.ProviderInterfaceHttpRepository;
import indi.uhyils.service.ProviderInterfaceHttpService;
import org.springframework.stereotype.Service;

/**
 * http接口子表(ProviderInterfaceHttp)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Service
@ReadWriteMark(tables = {"sys_provider_interface_http"})
public class ProviderInterfaceHttpServiceImpl extends AbstractDoService<ProviderInterfaceHttpDO, ProviderInterfaceHttp, ProviderInterfaceHttpDTO, ProviderInterfaceHttpRepository, ProviderInterfaceHttpAssembler> implements ProviderInterfaceHttpService {

    public ProviderInterfaceHttpServiceImpl(ProviderInterfaceHttpAssembler assembler, ProviderInterfaceHttpRepository repository) {
        super(assembler, repository);
    }


}
