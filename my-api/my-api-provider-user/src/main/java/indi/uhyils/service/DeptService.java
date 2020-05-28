package indi.uhyils.service;

import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.request.IdsRequest;
import indi.uhyils.pojo.request.PutPowersToDeptRequest;
import indi.uhyils.pojo.response.ServiceResult;

/**
 * 权限集接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface DeptService extends DefaultEntityService<DeptEntity> {

    /**
     * 给权限集添加权限
     *
     * @param request
     * @return
     */
    ServiceResult<Boolean> putPowersToDept(PutPowersToDeptRequest request);

    /**
     * 删除 -> 真删. 不是假删
     *
     * @param idsRequest
     * @return
     */
    ServiceResult<Boolean> deleteDeptPower(IdsRequest idsRequest);

}
