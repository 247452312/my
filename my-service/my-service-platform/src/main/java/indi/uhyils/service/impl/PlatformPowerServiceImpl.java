package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PlatformPowerAssembler;
import indi.uhyils.pojo.DO.PlatformPowerDO;
import indi.uhyils.pojo.DTO.PlatformPowerDTO;
import indi.uhyils.pojo.entity.PlatformPower;
import indi.uhyils.repository.PlatformPowerRepository;
import indi.uhyils.service.PlatformPowerService;
import org.springframework.stereotype.Service;

/**
* 权限表(PlatformPower)表 内部服务实现类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Service
@ReadWriteMark(tables = {"sys_platform_power"})
public class PlatformPowerServiceImpl extends AbstractDoService<PlatformPowerDO, PlatformPower, PlatformPowerDTO, PlatformPowerRepository, PlatformPowerAssembler> implements PlatformPowerService {

    public PlatformPowerServiceImpl(PlatformPowerAssembler assembler, PlatformPowerRepository repository) {
        super(assembler, repository);
    }


}
