package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.JobAssembler;
import indi.uhyils.dao.JobDao;
import indi.uhyils.pojo.DO.JobDO;
import indi.uhyils.pojo.DTO.JobDTO;
import indi.uhyils.pojo.entity.Job;
import indi.uhyils.repository.JobRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 定时任务表(Job)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分59秒
 */
@Repository
public class JobRepositoryImpl extends AbstractRepository<Job, JobDO, JobDao, JobDTO, JobAssembler> implements JobRepository {

    protected JobRepositoryImpl(JobAssembler convert, JobDao dao) {
        super(convert, dao);
    }


}
