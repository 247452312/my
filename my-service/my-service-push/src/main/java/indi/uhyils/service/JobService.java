package indi.uhyils.service;


import indi.uhyils.pojo.DTO.JobDTO;
import indi.uhyils.pojo.cqe.query.IdQuery;

/**
 * 定时任务表(Job)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时46分59秒
 */
public interface JobService extends BaseDoService<JobDTO> {

    /**
     * 暂停定时任务
     *
     * @param request id
     *
     * @return 是否成功
     */
    Boolean pause(IdQuery request);

    /**
     * 开启定时任务
     *
     * @param request id
     *
     * @return 是否成功
     */
    Boolean start(IdQuery request);

    /**
     * 执行定时任务
     *
     * @param request id
     *
     * @return 是否成功
     */
    Boolean test(IdQuery request);

}
