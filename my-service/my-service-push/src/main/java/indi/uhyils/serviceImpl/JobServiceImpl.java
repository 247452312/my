package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.content.Content;
import indi.uhyils.dao.JobDao;
import indi.uhyils.enum_.CacheTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.JobEntity;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.request.base.ObjRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.JobService;
import indi.uhyils.util.ScheduledManager;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@Service(group = "${spring.profiles.active}")
@ReadWriteMark(tables = {"sys_job"}, cacheType = CacheTypeEnum.ALL_TYPE)
public class JobServiceImpl extends BaseDefaultServiceImpl<JobEntity> implements JobService {

    /**
     * 工具entity, 插入日志时用来插入登录日志
     */
    private final UserEntity userEntity = new UserEntity();
    @Autowired
    private JobDao dao;
    @Autowired
    private ScheduledManager scheduledManager;

    {
        // 日志的创建用户一定是超级管理员
        userEntity.setId(Content.ADMIN_USER_ID);
    }

    public JobDao getDao() {
        return dao;
    }

    public void setDao(JobDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> insert(ObjRequest<JobEntity> insert) {
        JobEntity data = insert.getData();
        data.preInsert(insert);
        int count = dao.insert(data);
        data.setUserEntity(userEntity);
        boolean add = scheduledManager.addJob(data);
        if (count == 1 && add) {
            return ServiceResult.buildSuccessResult("创建成功", count, insert);
        }
        return ServiceResult.buildFailedResult("创建失败", 0, insert);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> update(ObjRequest<JobEntity> update) {
        JobEntity data = update.getData();
        data.preUpdate(update);
        int count = getDao().update(data);
        data.setUserEntity(userEntity);
        boolean remove = scheduledManager.deleteJob(data);
        boolean add = scheduledManager.addJob(data);
        if (count != 0 && remove && add) {
            return ServiceResult.buildSuccessResult("修改成功", count, update);
        } else {
            return ServiceResult.buildFailedResult("修改失败", 0, update);
        }
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> pause(IdRequest request) throws ClassNotFoundException {
        String id = request.getId();
        JobEntity byId = dao.getById(id);
        byId.setUserEntity(userEntity);
        dao.pause(id);
        boolean remove = scheduledManager.pauseJob(byId);
        if (remove) {
            return ServiceResult.buildSuccessResult("停止成功", true, request);
        }
        return ServiceResult.buildFailedResult("停止失败", false, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> start(IdRequest request) {
        String id = request.getId();
        JobEntity byId = dao.getById(id);
        byId.setUserEntity(userEntity);
        dao.start(id);
        boolean add = scheduledManager.resumeJob(byId);
        if (add) {
            return ServiceResult.buildSuccessResult("开启成功", true, request);
        }
        return ServiceResult.buildFailedResult("开启失败", false, request);
    }

    @Override
    public ServiceResult<Boolean> test(IdRequest request) throws ClassNotFoundException {
        JobEntity byId = dao.getById(request.getId());
        byId.setUserEntity(request.getUser());
        boolean test = scheduledManager.runJobNow(byId);
        if (test) {
            return ServiceResult.buildSuccessResult("测试成功", true, request);
        }
        return ServiceResult.buildFailedResult("测试失败", false, request);
    }
}
