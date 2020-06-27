package indi.uhyils.service;

import indi.uhyils.pojo.model.JobEntity;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

/**
 * 定时任务管理表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时22分
 */
public interface JobService extends DefaultEntityService<JobEntity> {

    /**
     * 暂停定时任务
     *
     * @param request id
     * @return 是否成功
     */
    ServiceResult<Boolean> pause(IdRequest request) throws ClassNotFoundException;
    /**
     * 开启定时任务
     *
     * @param request id
     * @return 是否成功
     */
    ServiceResult<Boolean> start(IdRequest request);
    /**
     * 执行定时任务
     *
     * @param request id
     * @return 是否成功
     */
    ServiceResult<Boolean> test(IdRequest request) throws ClassNotFoundException;
}
