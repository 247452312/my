package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ProviderInterfaceMysqlAssembler;
import indi.uhyils.pojo.DO.ProviderInterfaceMysqlDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceMysqlDTO;
import indi.uhyils.pojo.entity.ProviderInterfaceMysql;
import indi.uhyils.repository.ProviderInterfaceMysqlRepository;
import indi.uhyils.service.ProviderInterfaceMysqlService;
import org.springframework.stereotype.Service;

/**
 * mysql接口子表(ProviderInterfaceMysql)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Service
@ReadWriteMark(tables = {"sys_provider_interface_mysql"})
public class ProviderInterfaceMysqlServiceImpl extends AbstractDoService<ProviderInterfaceMysqlDO, ProviderInterfaceMysql, ProviderInterfaceMysqlDTO, ProviderInterfaceMysqlRepository, ProviderInterfaceMysqlAssembler> implements ProviderInterfaceMysqlService {

    public ProviderInterfaceMysqlServiceImpl(ProviderInterfaceMysqlAssembler assembler, ProviderInterfaceMysqlRepository repository) {
        super(assembler, repository);
    }


}
