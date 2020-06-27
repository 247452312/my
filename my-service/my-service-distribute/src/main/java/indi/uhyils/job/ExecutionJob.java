package indi.uhyils.job;

import indi.uhyils.dao.JobDao;
import indi.uhyils.pojo.model.JobEntity;
import indi.uhyils.thread.ThreadPoolExecutorUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.ScheduledManager;
import indi.uhyils.util.SpringUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月26日 08时26分
 */
@Async
public class ExecutionJob extends QuartzJobBean {

    /**
     * 执行定时任务线程池
     */
    private static final ThreadPoolExecutor EXECUTOR = ThreadPoolExecutorUtil.getPoll();

    /**
     * 执行定时任务
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobEntity quartzJob = (JobEntity) jobExecutionContext.getMergedJobDataMap().get(JobConfig.JOB_KEY);
        // 获取spring bean
        JobDao dao = SpringUtil.getBean(JobDao.class);
        ScheduledManager manager = SpringUtil.getBean(ScheduledManager.class);
        try {
            // 执行任务
            LogUtil.info(this, "任务准备执行，任务名称：" + quartzJob.getInterfaceName() + "");
            JobRunnable task = new JobRunnable(quartzJob.getInterfaceName(), quartzJob.getMethodName(),
                    quartzJob.getParams(), quartzJob.getParamType(), quartzJob.getUserEntity());
            EXECUTOR.submit(task);
            // 任务状态
            LogUtil.info(this, "任务执行完毕，任务名称：" + quartzJob.getName());
        } catch (Exception e) {
            LogUtil.error(this, e);
            quartzJob.setPause(false);
            //更新状态
            dao.update(quartzJob);
            manager.deleteJob(quartzJob);
        }
    }
}
