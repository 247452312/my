package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.context.MyContext;
import indi.uhyils.dao.JobDao;
import indi.uhyils.enum_.CacheTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.JobDO;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.request.base.ObjRequest;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.util.ScheduledManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@RpcService
@ReadWriteMark(tables = {"sys_job"}, cacheType = CacheTypeEnum.ALL_TYPE)
public class JobProvider extends BaseDefaultProvider<JobDO> implements indi.uhyils.protocol.rpc.provider.JobProvider {

    /**
     * 工具entity, 插入日志时用来插入登录日志
     */
    private final UserDO userEntity = new UserDO();
    @Resource
    private JobDao dao;
    @Autowired
    private ScheduledManager scheduledManager;

    {
        // 日志的创建用户一定是超级管理员
        userEntity.setId(MyContext.ADMIN_USER_ID);
    }

    @Override
    public JobDao getDao() {
        return dao;
    }

    public void setDao(JobDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> insert(ObjRequest<JobDO> insert) throws Exception {
        JobDO data = insert.getData();
        data.preInsert(insert);
        int count = dao.insert(data);
        data.setUserEntity(userEntity);
        boolean add = scheduledManager.addJob(data);
        if (count == 1 && add) {
            return ServiceResult.buildSuccessResult("创建成功", count);
        }
        return ServiceResult.buildFailedResult("创建失败", 0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> update(ObjRequest<JobDO> update) {
        JobDO data = update.getData();
        data.preUpdate(update);
        int count = getDao().update(data);
        data.setUserEntity(userEntity);
        boolean remove = scheduledManager.deleteJob(data);
        boolean add = scheduledManager.addJob(data);
        if (count != 0 && remove && add) {
            return ServiceResult.buildSuccessResult("修改成功", count);
        } else {
            return ServiceResult.buildFailedResult("修改失败", 0);
        }
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> pause(IdRequest request) throws ClassNotFoundException {
        Long id = request.getId();
        JobDO byId = dao.getById(id);
        byId.setUserEntity(userEntity);
        dao.pause(id);
        boolean remove = scheduledManager.pauseJob(byId);
        if (remove) {
            return ServiceResult.buildSuccessResult("停止成功", Boolean.TRUE);
        }
        return ServiceResult.buildFailedResult("停止失败", Boolean.FALSE);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> start(IdRequest request) {
        Long id = request.getId();
        JobDO byId = dao.getById(id);
        byId.setUserEntity(userEntity);
        dao.start(id);
        boolean add = scheduledManager.resumeJob(byId);
        if (add) {
            return ServiceResult.buildSuccessResult("开启成功", Boolean.TRUE);
        }
        return ServiceResult.buildFailedResult("开启失败", Boolean.FALSE);
    }

    @Override
    public ServiceResult<Boolean> test(IdRequest request) throws ClassNotFoundException {
        JobDO byId = dao.getById(request.getId());
        byId.setUserEntity(request.getUser());
        boolean test = scheduledManager.runJobNow(byId);
        if (test) {
            return ServiceResult.buildSuccessResult("测试成功", Boolean.TRUE);
        }
        return ServiceResult.buildFailedResult("测试失败", Boolean.FALSE);
    }
}
