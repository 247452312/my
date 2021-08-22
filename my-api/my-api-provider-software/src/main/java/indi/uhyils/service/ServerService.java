package indi.uhyils.service;

import indi.uhyils.pojo.model.ServerDO;
import indi.uhyils.pojo.request.GetNameByIdRequest;
import indi.uhyils.pojo.request.TestConnByDataRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultDOService;
import java.util.ArrayList;

/**
 * 服务器信息端口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 13时15分
 */
public interface ServerService extends DefaultDOService<ServerDO> {

    /**
     * 测试服务器链接
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> testConnByData(TestConnByDataRequest request);

    /**
     * 测试服务器链接
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> testConnById(IdRequest request);

    /**
     * 获取所有服务器信息
     *
     * @param request 请求
     *
     * @return 所有服务器信息
     */
    ServiceResult<ArrayList<ServerDO>> getServersIdAndName(DefaultRequest request);


    /**
     * 根据服务器的id获取服务器的用户名
     *
     * @param request 服务器id
     *
     * @return 服务器名称
     */
    ServiceResult<String> getNameById(GetNameByIdRequest request);

}
