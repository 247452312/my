package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.ServerDO;
import indi.uhyils.pojo.DTO.ServerDTO;
import indi.uhyils.pojo.entity.Server;

/**
 * 服务器表(Server)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分16秒
 */
@Assembler
public class ServerAssembler extends AbstractAssembler<ServerDO, Server, ServerDTO> {

    @Override
    public Server toEntity(ServerDO dO) {
        return new Server(dO);
    }

    @Override
    public Server toEntity(ServerDTO dto) {
        return new Server(toDo(dto));
    }

    @Override
    protected Class<ServerDO> getDoClass() {
        return ServerDO.class;
    }

    @Override
    protected Class<ServerDTO> getDtoClass() {
        return ServerDTO.class;
    }
}

