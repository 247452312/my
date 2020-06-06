package indi.uhyils.service;

import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.request.IdsRequest;
import indi.uhyils.pojo.request.PutDeptsToMenuRequest;
import indi.uhyils.pojo.request.PutMenusToDeptsRequest;
import indi.uhyils.pojo.request.PutPowersToDeptRequest;
import indi.uhyils.pojo.response.ServiceResult;

import java.util.ArrayList;

/**
 * 权限集接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface DeptService extends DefaultEntityService<DeptEntity> {

    /**
     * 给权限集添加权限
     *
     * @param request 将权限授予权限集的请求
     * @return
     */
    ServiceResult<Boolean> putPowersToDept(PutPowersToDeptRequest request);

    /**
     * 删除 -> 真删. 不是假删
     * 解除权限和权限集的关联关系
     *
     * @param idsRequest 权限ids
     * @return
     */
    ServiceResult<Boolean> deleteDeptPower(IdsRequest idsRequest);


    /**
     * 将许多菜单添加到一个权限集
     *
     * @param request 将许多菜单添加到一个权限集的请求
     * @return 是否成功
     */
    ServiceResult<Boolean> putMenusToDept(PutMenusToDeptsRequest request);

    /**
     * 将许多权限集添加到一个菜单
     *
     * @param request 将许多权限集添加到一个菜单的请求
     * @return 是否成功
     */
    ServiceResult<Boolean> putDeptsToMenu(PutDeptsToMenuRequest request);

    /**
     * 获取全部权限集
     *
     * @param request 请求
     * @return 权限集
     */
    ServiceResult<ArrayList<DeptEntity>> getDepts(PutDeptsToMenuRequest request);

}
