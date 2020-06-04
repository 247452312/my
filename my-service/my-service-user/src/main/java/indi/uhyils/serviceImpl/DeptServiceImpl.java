package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.DeptDao;
import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.DeptPowerMiddle;
import indi.uhyils.pojo.request.IdsRequest;
import indi.uhyils.pojo.request.PutPowersToDeptRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@Service(group = "${spring.profiles.active}")
public class DeptServiceImpl extends BaseDefaultServiceImpl<DeptEntity> implements DeptService {
    @Autowired
    private DeptDao dao;

    @Override
    public ServiceResult<Boolean> putPowersToDept(PutPowersToDeptRequest request) {
        String deptId = request.getDeptId();
        for (String powerId : request.getPowerIds()) {
            DeptPowerMiddle middle = DeptPowerMiddle.build(deptId, powerId);
            middle.preInsert();
            dao.insertDeptPower(middle);
        }
        return ServiceResult.buildSuccessResult("权限集添加权限成功", true, request);
    }

    @Override
    public ServiceResult<Boolean> deleteDeptPower(IdsRequest idsRequest) {
        dao.deleteDeptPower(idsRequest.getIds());
        return ServiceResult.buildSuccessResult("删除成功", true, idsRequest);
    }


    public DeptDao getDao() {
        return dao;
    }

    public void setDao(DeptDao dao) {
        this.dao = dao;
    }
}
