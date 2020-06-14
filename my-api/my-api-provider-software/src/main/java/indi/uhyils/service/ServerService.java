package indi.uhyils.service;

import indi.uhyils.pojo.model.ServerEntity;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.GetNameByIdRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.TestConnByDataRequest;
import indi.uhyils.pojo.response.ServiceResult;

import java.util.ArrayList;

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

    /**
     * 获取所有服务器信息
     *
     * @param request 请求
     * @return 所有服务器信息
     */
    ServiceResult<ArrayList<ServerEntity>> getServersIdAndName(DefaultRequest request);


    /**
     * 根据服务器的id获取服务器的用户名
     *
     * @param request 服务器id
     * @return 服务器名称
     */
    ServiceResult<String> getNameById(GetNameByIdRequest request);

}
