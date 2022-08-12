package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.DeptDTO;
import indi.uhyils.pojo.DTO.request.PutMenusToDeptsCommand;
import indi.uhyils.pojo.DTO.request.PutPowersToDeptCommand;
import indi.uhyils.pojo.DTO.response.GetAllPowerWithHaveMarkDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.List;

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
    Boolean putPowersToDept(PutPowersToDeptCommand request) throws Exception;

    /**
     * 删除 -> 真删. 不是假删
     * 解除权限和权限集的关联关系
     *
     * @param idsRequest 权限ids
     *
     * @return
     */
    Boolean deleteDeptPower(IdsCommand idsRequest);


    /**
     * 将许多菜单添加到一个权限集
     *
     * @param request 将许多菜单添加到一个权限集的请求
     *
     * @return 是否成功
     */
    Boolean putMenusToDept(PutMenusToDeptsCommand request);

    /**
     * 获取全部权限集
     *
     * @param request 请求
     *
     * @return 权限集
     */
    List<DeptDTO> getDepts(DefaultCQE request);

    /**
     * 获取所有权限
     *
     * @param request
     *
     * @return
     */
    List<GetAllPowerWithHaveMarkDTO> getAllPowerWithHaveMark(IdQuery request);


    /**
     * 根据权限集id删除 并且删除关联表
     *
     * @param request 权限集id
     *
     * @return 删除是否成功
     */
    Boolean deleteDept(IdCommand request);


}
