package indi.uhyils.pojo.entity;

import indi.uhyils.enum_.SoftwareStatusEnum;
import indi.uhyils.pojo.DO.SoftwareDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ServerRepository;
import indi.uhyils.repository.SoftwareRepository;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.SocketUtil;
import indi.uhyils.util.SshUtils;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * 中间件表(Software)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月02日 08时42分20秒
 */
public class Software extends AbstractDoEntity<SoftwareDO> {

    protected Server server;

    public Software(SoftwareDO dO) {
        super(dO);
    }

    public Software(Long id) {
        super(id, new SoftwareDO());
    }

    public Software(Long id, SoftwareRepository rep) {
        super(id, new SoftwareDO());
        completion(rep);
    }

    public void start() {
        checkStatus(SoftwareStatusEnum.STOP);
        String startSh = toDo().getStartSh();
        AssertUtil.assertTrue(StringUtils.isNotBlank(startSh));
        SshUtils.execCommandBySsh(server.toDo(), startSh);
    }

    public void stop() {
        checkStatus(SoftwareStatusEnum.RUNNING);
        String stopSh = toDo().getStopSh();
        AssertUtil.assertTrue(StringUtils.isNotBlank(stopSh));
        SshUtils.execCommandBySsh(server.toDo(), stopSh);
    }

    public void link() {
        AssertUtil.assertTrue(server != null, "连接不能没有 server");
        boolean canConnect = SocketUtil.canConnect(server.toDo().getIp(), toDo().getPort());
        AssertUtil.assertTrue(canConnect, "不能连接");
    }

    public void close() {
        throw new RuntimeException("错误,不能使用基类关闭,请子类实现");
    }


    public List<String> keys() {
        return null;
    }

    /**
     * 填充服务器信息
     *
     * @param serverRepository
     */
    public void findServer(ServerRepository serverRepository) {
        if (server != null) {
            return;
        }
        this.server = new Server(toDo().getServerId(), serverRepository);

    }

    public void initBaseInfo() {
        throw new RuntimeException("错误,不能使用基类获取详情,请子类实现");
    }

    public Identifier saveSelf(SoftwareRepository rep) {
        return rep.save(this);
    }

    public void checkStatus(SoftwareStatusEnum statusEnum) {
        SoftwareStatusEnum status = getStatus();
        AssertUtil.assertTrue(status == statusEnum, "状态错误: " + status.name());
    }

    /**
     * 获取状态
     *
     * @return
     */
    protected SoftwareStatusEnum getStatus() {
        String statusSh = toDo().getStatusSh();
        AssertUtil.assertTrue(StringUtils.isNotBlank(statusSh));
        String status = SshUtils.execCommandBySsh(server.toDo(), statusSh);
        if (status.contains("ERROR")) {
            return SoftwareStatusEnum.ERROR;
        } else if (status.contains("STOP")) {
            return SoftwareStatusEnum.STOP;
        }
        return SoftwareStatusEnum.RUNNING;
    }
}
