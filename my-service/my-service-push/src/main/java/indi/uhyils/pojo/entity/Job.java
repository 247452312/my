package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.JobDO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.repository.JobRepository;
import indi.uhyils.util.ScheduledManager;
import indi.uhyils.util.SpringUtil;

/**
 * 定时任务表(Job)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月02日 19时46分58秒
 */
public class Job extends AbstractDoEntity<JobDO> {

    private ScheduledManager scheduledManager;

    private UserDTO user;

    @Default
    public Job(JobDO data) {
        super(data);
    }

    public Job(Long id) {
        super(id, new JobDO());
    }

    public Job(Long id, JobRepository rep) {
        super(id, new JobDO());
        completion(rep);
    }

    private void initScheduled() {
        if (this.scheduledManager != null) {
            return;
        }
        scheduledManager = SpringUtil.getBean(ScheduledManager.class);

    }

    public void fillUser(UserDTO user) {
        if (this.user != null) {
            return;
        }
        this.user = user;
    }

    public void addSelfToJob() {
        initScheduled();
        scheduledManager.addJob(toData());

    }

    public void delJob() {
        initScheduled();
        scheduledManager.deleteJob(toData());
    }

    public void pause() {
        initScheduled();
        toData().setPause(true);
        scheduledManager.pauseJob(toData());

    }

    public void start() {
        initScheduled();
        toData().setPause(false);
        scheduledManager.resumeJob(toData());
    }

    public void test() {
        initScheduled();
        scheduledManager.runJobNow(toData());
    }
}
