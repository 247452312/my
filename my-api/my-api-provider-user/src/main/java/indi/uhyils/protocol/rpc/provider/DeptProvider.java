package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.pojo.DTO.request.PutDeptsToMenuRequest;
import indi.uhyils.pojo.DTO.request.PutMenusToDeptsRequest;
import indi.uhyils.pojo.DTO.request.PutPowersToDeptCommand;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.request.base.IdsRequest;
import indi.uhyils.pojo.DTO.response.GetAllMenuWithHaveMarkResponse;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.ArrayList;

/**
 * 权限集接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface DeptProvider extends DTOProvider<DeptDTO> {

    /**
     * 给权限集添加权限
     *
     * @param request 将权限授予权限集的请求
     *
     * @return
     */
    ServiceResult<Boolean> putPowersToDept(PutPowersToDeptCommand request) throws Exception;

    /**
     * 删除 -> 真删. 不是假删
     * 解除权限和权限集的关联关系
     *
     * @param idsRequest 权限ids
     *
     * @return
     */
    ServiceResult<Boolean> deleteDeptPower(IdsRequest idsRequest);


    /**
     * 将许多菜单添加到一个权限集
     *
     * @param request 将许多菜单添加到一个权限集的请求
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> putMenusToDept(PutMenusToDeptsRequest request);

    /**
     * 将许多权限集添加到一个菜单
     *
     * @param request 将许多权限集添加到一个菜单的请求
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> putDeptsToMenu(PutDeptsToMenuRequest request);

    /**
     * 获取全部权限集
     *
     * @param request 请求
     *
     * @return 权限集
     */
    ServiceResult<ArrayList<DeptDO>> getDepts(PutDeptsToMenuRequest request);


    /**
     * 获取所有叶子菜单(包含羁绊标记)
     *
     * @param request 权限集id
     *
     * @return 所有叶子菜单(包含羁绊标记)
     */
    ServiceResult<ArrayList<GetAllMenuWithHaveMarkResponse>> getAllMenuWithHaveMark(IdRequest request);

    /**
     * 获取所有
     *
     * @param request
     *
     * @return
     */
    ServiceResult<ArrayList<GetAllPowerWithHaveMarkResponse>> getAllPowerWithHaveMark(IdRequest request);


    /**
     * 根据权限集id删除 并且删除关联表
     *
     * @param request 权限集id
     *
     * @return 删除是否成功
     */
    ServiceResult<Boolean> deleteDept(IdRequest request);


}
