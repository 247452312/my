package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ServerAssembler;
import indi.uhyils.dao.ServerDao;
import indi.uhyils.pojo.DO.ServerDO;
import indi.uhyils.pojo.DTO.ServerDTO;
import indi.uhyils.pojo.entity.Server;
import indi.uhyils.repository.ServerRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;


/**
 * 服务器表(Server)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分17秒
 */
@Repository
public class ServerRepositoryImpl extends AbstractRepository<Server, ServerDO, ServerDao, ServerDTO, ServerAssembler> implements ServerRepository {

    protected ServerRepositoryImpl(ServerAssembler convert, ServerDao dao) {
        super(convert, dao);
    }


    @Override
    public List<Server> findServersIdAndName() {
        List<ServerDO> serversIdAndName = dao.getServersIdAndName();
        return assembler.listToEntity(serversIdAndName);
    }
}
