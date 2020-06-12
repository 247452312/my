package indi.uhyils.service;

import indi.uhyils.pojo.model.ServerEntity;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.TestConnByDataRequest;
import indi.uhyils.pojo.response.ServiceResult;

/**
 * 服务器信息端口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 13时15分
 */
public interface ServerService extends DefaultEntityService<ServerEntity> {

    /**
     * 测试服务器链接
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> testConnByData(TestConnByDataRequest request);
    /**
     * 测试服务器链接
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> testConnById(IdRequest request);

}
