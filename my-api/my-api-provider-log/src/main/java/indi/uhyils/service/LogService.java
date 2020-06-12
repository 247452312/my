package indi.uhyils.service;

import indi.uhyils.pojo.model.LogEntity;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.response.ServiceResult;

/**
 * 日志接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface LogService extends DefaultEntityService<LogEntity> {

    /**
     * 推送请求日志
     *
     * @param request 请求
     * @return 是否成功
     */
    ServiceResult<Boolean> pushRequestLogNoToken(ObjRequest<LogEntity> request);


}
