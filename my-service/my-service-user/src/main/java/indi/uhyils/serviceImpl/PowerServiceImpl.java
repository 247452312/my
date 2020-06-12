package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@Service(group = "${spring.profiles.active}")
public class PowerServiceImpl extends BaseDefaultServiceImpl<PowerEntity> implements PowerService {

    private static final Logger logger = LoggerFactory.getLogger(PowerServiceImpl.class);

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
        List<PowerEntity> byId = getDao().getById(request.getId());
        if (byId == null) {
            return ServiceResult.buildFailedResult("查无此人", null, request);
        }
        PowerEntity powerEntity = byId.get(0);
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
    public ServiceResult<Boolean> initPowerInProStartNoToken(DefaultRequest request) {
        List<PowerSimpleEntity> powersSingle;
        try {
            powersSingle = ApiPowerInitUtil.getPowersSingle();
        } catch (IOException e) {
            logger.error(e.getMessage());
            return ServiceResult.buildErrorResult("初始化power失败:" + e.getMessage(), request);
        }
        ArrayList<PowerSimpleEntity> result = new ArrayList<>();
        boolean b = true;
        for (PowerSimpleEntity powerSimpleEntity : powersSingle) {
            Integer count = dao.checkPower(powerSimpleEntity.getInterfaceName(), powerSimpleEntity.getMethodName());
            // 如果数据库中不存在此权限
            if (count == 0) {
                PowerEntity powerEntity = PowerEntity.build(powerSimpleEntity);
                powerEntity.preInsert(request);
                int insert = dao.insert(powerEntity);
                if (insert != 1) {
                    b = false;
                }
            }
        }
        return ServiceResult.buildSuccessResult("初始化power成功", b, request);
    }

}
