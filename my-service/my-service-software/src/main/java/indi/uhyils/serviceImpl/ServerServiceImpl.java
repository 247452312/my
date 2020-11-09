package indi.uhyils.serviceImpl;

import indi.uhyils.dao.ServerDao;
import indi.uhyils.pojo.model.ServerEntity;
import indi.uhyils.pojo.request.GetNameByIdRequest;
import indi.uhyils.pojo.request.TestConnByDataRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.ServerService;
import indi.uhyils.util.SshUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * 服务器接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 13时34分
 */
@Service(group = "${spring.profiles.active}")
public class ServerServiceImpl extends BaseDefaultServiceImpl<ServerEntity> implements ServerService {

    @Autowired
    private ServerDao dao;

    public ServerDao getDao() {
        return dao;
    }

    public void setDao(ServerDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<Boolean> testConnByData(TestConnByDataRequest request) {
        Boolean aBoolean = SshUtils.testConn(request.getIp(), request.getPort(), request.getUsername(), request.getPassword());
        return ServiceResult.buildSuccessResult("测试成功", aBoolean, request);
    }

    @Override
    public ServiceResult<Boolean> testConnById(IdRequest request) {
        ServerEntity serverEntity = dao.getById(request.getId());
        if (serverEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null, request);
        }
        Boolean aBoolean = SshUtils.testConn(serverEntity.getIp(), serverEntity.getPort(), serverEntity.getUsername(), serverEntity.getPassword());
        return ServiceResult.buildSuccessResult("测试成功", aBoolean, request);
    }

    @Override
    public ServiceResult<ArrayList<ServerEntity>> getServersIdAndName(DefaultRequest request) {
        ArrayList<ServerEntity> list = dao.getServersIdAndName();
        return ServiceResult.buildSuccessResult("查询成功", list, request);
    }

    @Override
    public ServiceResult<String> getNameById(GetNameByIdRequest request) {
        String name = dao.getNameById(request.getId());
        return ServiceResult.buildSuccessResult("查询服务器名称成功", name, request);
    }
}
