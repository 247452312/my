package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.NoToken;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.PowerDao;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.PowerEntity;
import indi.uhyils.pojo.model.PowerSimpleEntity;
import indi.uhyils.pojo.request.CheckUserHavePowerRequest;
import indi.uhyils.pojo.request.GetMethodNameByInterfaceNameRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.PowerService;
import indi.uhyils.util.ApiPowerInitUtil;
import indi.uhyils.util.LogUtil;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@Service(group = "${spring.profiles.active}")
@ReadWriteMark(tables = {"sys_power"})
public class PowerServiceImpl extends BaseDefaultServiceImpl<PowerEntity> implements PowerService {


    @Resource
    private PowerDao dao;


    public PowerDao getDao() {
        return dao;
    }

    public void setDao(PowerDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<ArrayList<PowerEntity>> getPowers(DefaultRequest request) {
        return ServiceResult.buildSuccessResult("获取成功", dao.getAll(), request);
    }

    @Override
    @NoToken
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_user", "sys_role", "sys_role_dept", "sys_dept", "sys_dept_power", "sys_power"})
    public ServiceResult<Boolean> checkUserHavePower(CheckUserHavePowerRequest request) {
        Integer count = dao.checkUserHavePower(request.getUserId(), request.getInterfaceName(), request.getMethodName());
        boolean havePower;
        if (count > 0) {
            havePower = true;
        } else {
            havePower = false;
        }
        return ServiceResult.buildSuccessResult("查询成功", havePower, request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dept_power"})
    public ServiceResult<Boolean> deletePower(IdRequest request) {
        PowerEntity powerEntity = getDao().getById(request.getId());
        if (powerEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null, request);
        }
        powerEntity.setDeleteFlag(true);
        powerEntity.preUpdate(request);
        dao.update(powerEntity);

        dao.deleteDeptPowerMiddleByPowerId(request.getId());

        return ServiceResult.buildSuccessResult("删除成功", true, request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_power"})
    public ServiceResult<ArrayList<String>> getInterfaces(DefaultRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", dao.getInterfaces(), request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_power"})
    public ServiceResult<ArrayList<String>> getMethodNameByInterfaceName(GetMethodNameByInterfaceNameRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", dao.getMethodNameByInterfaceName(request.getInterfaceName()), request);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_power"})
    public ServiceResult<Integer> initPowerInProStart(DefaultRequest request) {
        List<PowerSimpleEntity> powersSingle;
        try {
            powersSingle = ApiPowerInitUtil.getPowersSingle();
        } catch (IOException e) {
            LogUtil.error(this, e);
            return ServiceResult.buildErrorResult("初始化power失败:" + e.getMessage(), request);
        }
        AtomicInteger newPowerCount = new AtomicInteger(0);
        for (PowerSimpleEntity powerSimpleEntity : powersSingle) {
            Integer count = dao.checkPower(powerSimpleEntity.getInterfaceName(), powerSimpleEntity.getMethodName());
            // 如果数据库中不存在此权限
            if (count == 0) {
                PowerEntity powerEntity = PowerEntity.build(powerSimpleEntity);
                powerEntity.preInsert(request);
                dao.insert(powerEntity);
                newPowerCount.incrementAndGet();

            }
        }
        return ServiceResult.buildSuccessResult("初始化power成功", newPowerCount.get(), request);
    }

}
