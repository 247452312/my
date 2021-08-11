package indi.uhyils.service;

import indi.uhyils.pojo.request.ExecuteCodeRequest;
import indi.uhyils.pojo.response.ExecuteCodeResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.BaseService;

/**
 * 解析代码的service
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月10日 08时45分
 */
public interface ParsingCodeService extends BaseService {

    /**
     * 解析代码
     *
     * @param request
     *
     * @return
     */
    ServiceResult<ExecuteCodeResponse> executeCode(ExecuteCodeRequest request);
}
