package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.ServerDO;
import indi.uhyils.repository.ServerRepository;
import indi.uhyils.util.SshUtils;

/**
 * 服务器表(Server)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月02日 08时42分15秒
 */
public class Server extends AbstractDoEntity<ServerDO> {

    public Server(ServerDO dO) {
        super(dO);
    }

    public Server(Long id, ServerRepository rep) {
        super(id, new ServerDO());
        completion(rep);
    }

    public Server(Long id) {
        super(id, new ServerDO());
    }

    public Boolean testConn() {
        return SshUtils.testConn(toDo().getIp(), toDo().getPort(), toDo().getUsername(), toDo().getPassword());
    }
}
