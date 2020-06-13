package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.content.Content;
import indi.uhyils.dao.LogDao;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.LogEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.LogService;
import indi.uhyils.util.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;

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
        userEntity.setId(Content.ADMIN_USER_ID);
    }

    @Override
    public ServiceResult<Boolean> pushRequestLogNoToken(ObjRequest<LogEntity> request) {
        LogEntity data = request.getData();
        request.setUser(userEntity);
        data.preInsert(request);
        dao.insert(data);
        return ServiceResult.buildSuccessResult("插入日志成功", true, request);
    }

    @Override
    public ServiceResult<ArrayList<Map<String, Object>>> getLogTypes(DefaultRequest request) {
        ArrayList<Map<String, Object>> enumList = EnumUtils.getEnumList(ServiceCode.class);
        return ServiceResult.buildSuccessResult("成功获取所有的日志类型", enumList, request);
    }
}
