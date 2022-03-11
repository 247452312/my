package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PlatformNodeLinkAssembler;
import indi.uhyils.pojo.DO.PlatformNodeLinkDO;
import indi.uhyils.pojo.DTO.PlatformNodeLinkDTO;
import indi.uhyils.pojo.entity.PlatformNodeLink;
import indi.uhyils.repository.PlatformNodeLinkRepository;
import indi.uhyils.service.PlatformNodeLinkService;
import org.springframework.stereotype.Service;

/**
* 节点关联表(PlatformNodeLink)表 内部服务实现类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Service
@ReadWriteMark(tables = {"sys_platform_node_link"})
public class PlatformNodeLinkServiceImpl extends AbstractDoService<PlatformNodeLinkDO, PlatformNodeLink, PlatformNodeLinkDTO, PlatformNodeLinkRepository, PlatformNodeLinkAssembler> implements PlatformNodeLinkService {

    public PlatformNodeLinkServiceImpl(PlatformNodeLinkAssembler assembler, PlatformNodeLinkRepository repository) {
        super(assembler, repository);
    }


}
