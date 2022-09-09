package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.CompanyPowerAssembler;
import indi.uhyils.pojo.DO.CompanyPowerDO;
import indi.uhyils.pojo.DTO.CompanyPowerDTO;
import indi.uhyils.pojo.entity.CompanyPower;
import indi.uhyils.repository.CompanyPowerRepository;
import indi.uhyils.service.CompanyPowerService;
import org.springframework.stereotype.Service;

/**
 * 厂商接口调用权限表(CompanyPower)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Service
@ReadWriteMark(tables = {"sys_company_power"})
public class CompanyPowerServiceImpl extends AbstractDoService<CompanyPowerDO, CompanyPower, CompanyPowerDTO, CompanyPowerRepository, CompanyPowerAssembler> implements CompanyPowerService {

    public CompanyPowerServiceImpl(CompanyPowerAssembler assembler, CompanyPowerRepository repository) {
        super(assembler, repository);
    }


}
