package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.JobAssembler;
import indi.uhyils.pojo.DO.JobDO;
import indi.uhyils.pojo.DTO.JobDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.entity.Job;
import indi.uhyils.repository.JobRepository;
import indi.uhyils.service.JobService;
import org.springframework.stereotype.Service;

/**
 * 定时任务表(Job)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分00秒
 */
@Service
@ReadWriteMark(tables = {"sys_job"})
public class JobServiceImpl extends AbstractDoService<JobDO, Job, JobDTO, JobRepository, JobAssembler> implements JobService {

    public JobServiceImpl(JobAssembler assembler, JobRepository repository) {
        super(assembler, repository);
    }

    @Override
    public Long add(AddCommand<JobDTO> addCommand) {
        JobDTO dto = addCommand.getDto();
        Job job = assem.toEntity(dto);
        rep.save(job);
        job.fillUser(addCommand.getUser());
        job.addSelfToJob();
        return 1L;
    }

    @Override
    public Integer update(ChangeCommand<JobDTO> changeCommand) {
        JobDTO dto = changeCommand.getDto();
        Job job = assem.toEntity(dto);
        rep.save(job);
        job.fillUser(changeCommand.getUser());
        job.delJob();
        job.addSelfToJob();
        return 1;
    }

    @Override
    public Boolean pause(IdQuery request) {
        Job job = new Job(request.getId());
        job.pause();
        rep.save(job);
        return true;
    }

    @Override
    public Boolean start(IdQuery request) {
        Job job = new Job(request.getId());
        job.start();
        rep.save(job);
        return true;
    }

    @Override
    public Boolean test(IdQuery request) {
        Job job = new Job(request.getId());
        job.completion(rep);
        job.fillUser(request.getUser());
        job.test();
        return true;
    }
}
