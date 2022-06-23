package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ServerAssembler;
import indi.uhyils.pojo.DO.ServerDO;
import indi.uhyils.pojo.DTO.ServerDTO;
import indi.uhyils.pojo.DTO.request.TestConnByDataRequest;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.Server;
import indi.uhyils.repository.ServerRepository;
import indi.uhyils.service.ServerService;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.SshUtils;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 服务器表(Server)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分17秒
 */
@Service
@ReadWriteMark(tables = {"sys_server"})
public class ServerServiceImpl extends AbstractDoService<ServerDO, Server, ServerDTO, ServerRepository, ServerAssembler> implements ServerService {

    public ServerServiceImpl(ServerAssembler assembler, ServerRepository repository) {
        super(assembler, repository);
    }


    @Override
    public Boolean testConnByData(TestConnByDataRequest request) {
        return SshUtils.testConn(request.getIp(), request.getPort(), request.getUsername(), request.getPassword());
    }

    @Override
    public Boolean testConnById(IdCommand request) {
        Server server = new Server(request.getId(), rep);
        return server.testConn();
    }

    @Override
    public List<ServerDTO> getServersIdAndName(DefaultCQE request) {
        List<Server> servers = rep.findServersIdAndName();
        return assem.listEntityToDTO(servers);
    }

    @Override
    public String getNameById(IdQuery request) {
        Server server = new Server(request.getId(), rep);
        return server.toData().map(ServerDO::getName).orElseThrow(() -> Asserts.makeException("未找到data"));
    }
}
