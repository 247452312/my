package indi.uhyils.repository;

import indi.uhyils.pojo.DO.ServerDO;
import indi.uhyils.pojo.entity.Server;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 服务器表(Server)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分16秒
 */
public interface ServerRepository extends BaseEntityRepository<ServerDO, Server> {


    /**
     * 获取全部
     *
     * @return
     */
    List<Server> findServersIdAndName();
}
