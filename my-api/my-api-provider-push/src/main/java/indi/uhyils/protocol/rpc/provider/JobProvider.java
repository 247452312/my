package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.JobDO;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.DefaultDTOProvider;

/**
 * 定时任务管理表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时22分
 */
public interface JobProvider extends DefaultDTOProvider<JobDO> {

    /**
     * 暂停定时任务
     *
     * @param request id
     *
     * @return 是否成功
     *
     * @throws ClassNotFoundException 参数类型错误
     */
    ServiceResult<Boolean> pause(IdRequest request) throws ClassNotFoundException;

    /**
     * 开启定时任务
     *
     * @param request id
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> start(IdRequest request);

    /**
     * 执行定时任务
     *
     * @param request id
     *
     * @return 是否成功
     *
     * @throws ClassNotFoundException 参数类型错误
     */
    ServiceResult<Boolean> test(IdRequest request) throws ClassNotFoundException;
}
