package indi.uhyils.util;

import static org.quartz.TriggerBuilder.newTrigger;

import indi.uhyils.pojo.DO.JobDO;
import indi.uhyils.protocol.task.ExecutionJob;
import indi.uhyils.protocol.task.JobConfig;
import java.util.Date;
import javax.annotation.Resource;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

/**
 * 定时任务管理类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 16时59分
 */
@Component
public class ScheduledManager {


    @Resource
    private Scheduler scheduler;

    public boolean addJob(JobDO jobEntity) {
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).
                withIdentity(JobConfig.JOB_NAME + jobEntity.getId()).build();

            //通过触发器名和cron 表达式创建 Trigger
            Trigger cronTrigger = newTrigger()
                .withIdentity(JobConfig.JOB_NAME + jobEntity.getId())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(jobEntity.getCron()))
                .build();

            cronTrigger.getJobDataMap().put(JobConfig.JOB_KEY, jobEntity);

            //重置启动时间
            ((CronTriggerImpl) cronTrigger).setStartTime(new Date());

            //执行定时任务
            scheduler.scheduleJob(jobDetail, cronTrigger);

            // 暂停任务
            if (jobEntity.getPause()) {
                pauseJob(jobEntity);
            }
        } catch (Exception e) {
            LogUtil.error(this, e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 更新job cron表达式
     *
     * @param quartzJob /
     */
    public boolean updateJobCron(JobDO quartzJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JobConfig.JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if (trigger == null) {
                addJob(quartzJob);
                trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            }
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCron());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置启动时间
            ((CronTriggerImpl) trigger).setStartTime(new Date());
            trigger.getJobDataMap().put(JobConfig.JOB_KEY, quartzJob);

            scheduler.rescheduleJob(triggerKey, trigger);
            // 暂停任务
            if (quartzJob.getPause()) {
                pauseJob(quartzJob);
            }
        } catch (Exception e) {
            LogUtil.error(this, e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;

    }

    /**
     * 删除一个job
     *
     * @param quartzJob /
     */
    public boolean deleteJob(JobDO quartzJob) {
        try {
            JobKey jobKey = JobKey.jobKey(JobConfig.JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            LogUtil.error(this, e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 恢复一个job
     *
     * @param quartzJob /
     */
    public boolean resumeJob(JobDO quartzJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JobConfig.JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if (trigger == null) {
                addJob(quartzJob);
            }
            JobKey jobKey = JobKey.jobKey(JobConfig.JOB_NAME + quartzJob.getId());
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            LogUtil.error(this, e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 立即执行job
     *
     * @param quartzJob /
     */
    public boolean runJobNow(JobDO quartzJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JobConfig.JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if (trigger == null) {
                addJob(quartzJob);
            }
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(JobConfig.JOB_KEY, quartzJob);
            JobKey jobKey = JobKey.jobKey(JobConfig.JOB_NAME + quartzJob.getId());
            scheduler.triggerJob(jobKey, dataMap);
        } catch (Exception e) {
            LogUtil.error(this, e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 暂停一个job
     *
     * @param quartzJob /
     */
    public boolean pauseJob(JobDO quartzJob) {
        try {
            JobKey jobKey = JobKey.jobKey(JobConfig.JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            LogUtil.error(this, e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
