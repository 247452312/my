package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.annotation.NoToken;
import indi.uhyils.dao.PowerDao;
import indi.uhyils.pojo.model.PowerEntity;
import indi.uhyils.pojo.model.PowerSimpleEntity;
import indi.uhyils.pojo.request.CheckUserHavePowerRequest;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.GetMethodNameByInterfaceNameRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.PowerService;
import indi.uhyils.util.ApiPowerInitUtil;
import indi.uhyils.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@Service(group = "${spring.profiles.active}")
public class PowerServiceImpl extends BaseDefaultServiceImpl<PowerEntity> implements PowerService {


    @Autowired
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
    public ServiceResult<Boolean> checkUserHavePowerNoToken(CheckUserHavePowerRequest request) {
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
    public ServiceResult<ArrayList<String>> getInterfaces(DefaultRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", dao.getInterfaces(), request);
    }

    @Override
    public ServiceResult<ArrayList<String>> getMethodNameByInterfaceName(GetMethodNameByInterfaceNameRequest request) {
        return ServiceResult.buildSuccessResult("查询成功", dao.getMethodNameByInterfaceName(request.getInterfaceName()), request);
    }

    @Override
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
