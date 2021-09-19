package indi.uhyils.pojo.entity;

import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.pojo.DO.LogMonitorJvmStatusDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.LogMonitorJvmStatusRepository;
import indi.uhyils.repository.LogMonitorRepository;
import indi.uhyils.util.AssertUtil;

/**
 * JVM状态子表(LogMonitorJvmStatus)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
public class LogMonitorJvmStatus extends AbstractDoEntity<LogMonitorJvmStatusDO> {

    private JvmUniqueMark unique;

    public LogMonitorJvmStatus(LogMonitorJvmStatusDO dO) {
        super(dO);
    }

    public LogMonitorJvmStatus(LogMonitorJvmStatusDO dO, JvmUniqueMark unique) {
        super(dO);
        this.unique = unique;
    }

    /**
     * 修改结束时间为假想时间
     *
     * @param rep
     */
    public void changeEndTimeLag(LogMonitorRepository rep) {
        long realEndTime = (long) (data.getTime() + RabbitMqContent.OUT_TIME * 60 * 1000 * RabbitMqContent.OUT_TIME_PRO);
        AssertUtil.assertTrue(data.getFid() != null);
        rep.changeEndTimeLag(this, realEndTime);
    }

    public Long fid() {
        return data.getFid();
    }

    public void fillFid(LogMonitorRepository repository) {
        if (fid() != null) {
            return;
        }
        AssertUtil.assertTrue(unique != null, "服务状态缺少唯一标示");
        Identifier idByUnique = repository.getIdByUnique(unique);
        data.setFid(idByUnique.getId());

    }

    public void addSelf(LogMonitorJvmStatusRepository rep) {
        rep.save(this);
    }
}
