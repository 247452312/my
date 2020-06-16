package indi.uhyils.service;

import indi.uhyils.pojo.model.LogEntity;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.response.ServiceResult;

import java.util.ArrayList;
import java.util.Map;

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
    ServiceResult<Boolean> pushRequestLog(ObjRequest<LogEntity> request);

    /**
     * 获取所有的日志类型 -- > 这个应该不用访问数据库
     *
     * @param request 请求
     * @return 所有的日志类型
     */
    ServiceResult<ArrayList<Map<String, Object>>> getLogTypes(DefaultRequest request);


}
