package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.ServerDao;
import indi.uhyils.pojo.model.ServerEntity;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.TestConnByDataRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.ServerService;
import indi.uhyils.util.SshUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        List<ServerEntity> byId = dao.getById(request.getId());
        if (byId == null || byId.size() != 1) {
            return ServiceResult.buildFailedResult("没有对应id", false, request);
        }
        ServerEntity serverEntity = byId.get(0);
        Boolean aBoolean = SshUtils.testConn(serverEntity.getIp(), serverEntity.getPort(), serverEntity.getUsername(), serverEntity.getPassword());
        return ServiceResult.buildSuccessResult("测试成功", aBoolean, request);
    }
}
