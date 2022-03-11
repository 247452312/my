package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PlatformSourceAssembler;
import indi.uhyils.pojo.DO.PlatformSourceDO;
import indi.uhyils.pojo.DO.PlatformSourceInterfaceDO;
import indi.uhyils.pojo.DTO.PlatformSourceDTO;
import indi.uhyils.pojo.cqe.command.PublishDbCommand;
import indi.uhyils.pojo.cqe.command.PublishHttpCommand;
import indi.uhyils.pojo.cqe.command.PublishRpcCommand;
import indi.uhyils.pojo.entity.PlatformSource;
import indi.uhyils.pojo.entity.PlatformSourceInterface;
import indi.uhyils.repository.PlatformSourceInterfaceRepository;
import indi.uhyils.repository.PlatformSourceRepository;
import indi.uhyils.service.PlatformSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 资源主表(PlatformSource)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
@Service
@ReadWriteMark(tables = {"sys_platform_source"})
public class PlatformSourceServiceImpl extends AbstractDoService<PlatformSourceDO, PlatformSource, PlatformSourceDTO, PlatformSourceRepository, PlatformSourceAssembler> implements PlatformSourceService {

    @Autowired
    private PlatformSourceInterfaceRepository interfaceRep;

    public PlatformSourceServiceImpl(PlatformSourceAssembler assembler, PlatformSourceRepository repository) {
        super(assembler, repository);
    }

    @Override
    public Boolean publishHttp(PublishHttpCommand request) {
        PlatformSourceInterfaceDO dO = assem.toInterfaceDo(request);
        PlatformSourceInterface sourceInterface = new PlatformSourceInterface(dO);
        sourceInterface.checkAndInitGroup(interfaceRep);


        return null;
    }

    @Override
    public Boolean publishRpc(PublishRpcCommand request) {
        return null;
    }

    @Override
    public Boolean publishDb(PublishDbCommand request) {
        return null;
    }
}
