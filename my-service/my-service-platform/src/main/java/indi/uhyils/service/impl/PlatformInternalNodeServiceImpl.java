package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PlatformInternalNodeAssembler;
import indi.uhyils.pojo.DO.PlatformInternalNodeDO;
import indi.uhyils.pojo.DTO.PlatformInternalNodeDTO;
import indi.uhyils.pojo.entity.PlatformInternalNode;
import indi.uhyils.repository.PlatformInternalNodeRepository;
import indi.uhyils.service.PlatformInternalNodeService;
import org.springframework.stereotype.Service;

/**
* 内部节点表(PlatformInternalNode)表 内部服务实现类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Service
@ReadWriteMark(tables = {"sys_platform_internal_node"})
public class PlatformInternalNodeServiceImpl extends AbstractDoService<PlatformInternalNodeDO, PlatformInternalNode, PlatformInternalNodeDTO, PlatformInternalNodeRepository, PlatformInternalNodeAssembler> implements PlatformInternalNodeService {

    public PlatformInternalNodeServiceImpl(PlatformInternalNodeAssembler assembler, PlatformInternalNodeRepository repository) {
        super(assembler, repository);
    }


}
