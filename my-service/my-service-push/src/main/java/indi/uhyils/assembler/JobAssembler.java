package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.JobDO;
import indi.uhyils.pojo.DTO.JobDTO;
import indi.uhyils.pojo.entity.Job;
import org.mapstruct.Mapper;

/**
 * 定时任务表(Job)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分58秒
 */
@Mapper(componentModel = "spring")
public abstract class JobAssembler extends AbstractAssembler<JobDO, Job, JobDTO> {

}
