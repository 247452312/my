package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ProviderInfoAssembler;
import indi.uhyils.pojo.DO.ProviderInfoDO;
import indi.uhyils.pojo.DTO.ProviderInfoDTO;
import indi.uhyils.pojo.cqe.command.RegisterProviderCommand;
import indi.uhyils.pojo.entity.ProviderInfo;
import indi.uhyils.repository.ProviderInfoRepository;
import indi.uhyils.service.ProviderInfoService;
import org.springframework.stereotype.Service;

/**
 * 服务提供者表(ProviderInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@Service
@ReadWriteMark(tables = {"sys_provider_info"})
public class ProviderInfoServiceImpl extends AbstractDoService<ProviderInfoDO, ProviderInfo, ProviderInfoDTO, ProviderInfoRepository, ProviderInfoAssembler> implements ProviderInfoService {

    public ProviderInfoServiceImpl(ProviderInfoAssembler assembler, ProviderInfoRepository repository) {
        super(assembler, repository);
    }


    @Override
    public ProviderInfoDTO registerProvider(RegisterProviderCommand command) {
        ProviderInfo providerInfo = assem.toEntity(command);
        // 检查名称是否有重复
        providerInfo.checkNameRepeat(rep);
        // 添加插入时默认信息
        providerInfo.injDefaultInfo();
        // 保存自身信息
        providerInfo.saveSelf(rep);
        return assem.toDTO(providerInfo);
    }
}
