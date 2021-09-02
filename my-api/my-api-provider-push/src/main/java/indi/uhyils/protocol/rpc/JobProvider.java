package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.JobDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * 定时任务表(Job)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分00秒
 */
public interface JobProvider extends DTOProvider<JobDTO> {

    /**
     * 暂停定时任务
     *
     * @param request id
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> pause(IdQuery request);

    /**
     * 开启定时任务
     *
     * @param request id
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> start(IdQuery request);

    /**
     * 执行定时任务
     *
     * @param request id
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> test(IdQuery request);

}

