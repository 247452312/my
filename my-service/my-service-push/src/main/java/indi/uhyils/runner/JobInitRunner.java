package indi.uhyils.runner;

import indi.uhyils.dao.JobDao;
import indi.uhyils.pojo.model.JobDO;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.ScheduledManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务初始化类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月27日 07时39分
 */
@Component
public class JobInitRunner implements ApplicationRunner {

    @Autowired
    private ScheduledManager scheduledManager;

    @Resource
    private JobDao jobDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<JobDO> list = jobDao.getAll();
        list.forEach(t -> {
            if (Boolean.FALSE.equals(t.getPause())) {
                scheduledManager.addJob(t);
            }
        });

        LogUtil.info(this, "定时任务初始化成功");
    }


    public ScheduledManager getScheduledManager() {
        return scheduledManager;
    }

    public void setScheduledManager(ScheduledManager scheduledManager) {
        this.scheduledManager = scheduledManager;
    }

    public JobDao getJobDao() {
        return jobDao;
    }

    public void setJobDao(JobDao jobDao) {
        this.jobDao = jobDao;
    }
}
