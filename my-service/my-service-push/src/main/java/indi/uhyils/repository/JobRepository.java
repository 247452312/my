package indi.uhyils.repository;

import indi.uhyils.pojo.DO.JobDO;
import indi.uhyils.pojo.entity.Job;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 定时任务表(Job)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分58秒
 */
public interface JobRepository extends BaseEntityRepository<JobDO, Job> {


}
