package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.LogDao;
import indi.uhyils.pojo.model.LogEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@Service(group = "${spring.profiles.active}")
public class LogServiceImpl extends BaseDefaultServiceImpl<LogEntity> implements LogService {

    @Autowired
    private LogDao dao;

    public LogDao getDao() {
        return dao;
    }

    public void setDao(LogDao dao) {
        this.dao = dao;
    }

    /**
     * 工具entity, 插入日志时用来插入登录日志
     */
    private UserEntity userEntity = new UserEntity();

    {
        // 日志的创建用户一定是超级管理员
        userEntity.setId("49ba59abbe56e057");
    }

    @Override
    public ServiceResult<Boolean> pushRequestLogNoToken(ObjRequest<LogEntity> request) {
        LogEntity data = request.getData();
        request.setUser(userEntity);
        data.preInsert(request);
        dao.insert(data);
        return ServiceResult.buildSuccessResult("插入日志成功", true, request);
    }
}
