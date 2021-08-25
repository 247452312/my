package indi.uhyils.serviceImpl;

import indi.uhyils.dao.ServerDao;
import indi.uhyils.pojo.DO.ServerDO;
import indi.uhyils.pojo.DTO.request.GetNameByIdRequest;
import indi.uhyils.pojo.DTO.request.TestConnByDataRequest;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.util.SshUtils;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 服务器接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 13时34分
 */
@RpcService
public class ServerProvider extends BaseDefaultProvider<ServerDO> implements indi.uhyils.protocol.rpc.provider.ServerProvider {

    @Resource
    private ServerDao dao;

    @Override
    public ServerDao getDao() {
        return dao;
    }

    public void setDao(ServerDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<Boolean> testConnByData(TestConnByDataRequest request) {
        Boolean aBoolean = SshUtils.testConn(request.getIp(), request.getPort(), request.getUsername(), request.getPassword());
        return ServiceResult.buildSuccessResult("测试成功", aBoolean);
    }

    @Override
    public ServiceResult<Boolean> testConnById(IdRequest request) {
        ServerDO serverEntity = dao.getById(request.getId());
        if (serverEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null);
        }
        Boolean aBoolean = SshUtils.testConn(serverEntity.getIp(), serverEntity.getPort(), serverEntity.getUsername(), serverEntity.getPassword());
        return ServiceResult.buildSuccessResult("测试成功", aBoolean);
    }

    @Override
    public ServiceResult<ArrayList<ServerDO>> getServersIdAndName(DefaultRequest request) {
        ArrayList<ServerDO> list = dao.getServersIdAndName();
        return ServiceResult.buildSuccessResult("查询成功", list);
    }

    @Override
    public ServiceResult<String> getNameById(GetNameByIdRequest request) {
        String name = dao.getNameById(request.getId());
        return ServiceResult.buildSuccessResult("查询服务器名称成功", name);
    }
}
