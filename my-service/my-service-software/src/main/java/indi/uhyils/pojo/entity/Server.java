package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.ServerDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.repository.ServerRepository;
import indi.uhyils.util.SshUtils;

/**
 * 服务器表(Server)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月02日 08时42分15秒
 */
public class Server extends AbstractDoEntity<ServerDO> {

    @Default
    public Server(ServerDO data) {
        super(data);
    }

    public Server(Long id, ServerRepository rep) {
        super(id, new ServerDO());
        completion(rep);
    }

    public Server(Long id) {
        super(id, new ServerDO());
    }

    public Boolean testConn() {
        return SshUtils.testConn(toData().getIp(), toData().getPort(), toData().getUsername(), toData().getPassword());
    }
}
