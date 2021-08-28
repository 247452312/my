package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.PowerDTO;
import indi.uhyils.pojo.cqe.query.CheckUserHavePowerQuery;
import indi.uhyils.pojo.DTO.request.GetMethodNameByInterfaceNameQuery;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.List;

/**
 * 权限接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface PowerProvider extends DTOProvider<PowerDTO> {

    /**
     * 获取所有的权限
     *
     * @param request 请求
     *
     * @return 所有权限
     */
    ServiceResult<List<PowerDTO>> getPowers(DefaultCQE request);

    /**
     * 检查用户是否存在此权限
     *
     * @param request 检查用户是否存在此权限请求
     *
     * @return 是否存在
     */
    ServiceResult<Boolean> checkUserHavePower(CheckUserHavePowerQuery request);

    /**
     * 删除权限->包括连接表
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> deletePower(IdCommand request);


    /**
     * 获取所有interfaceName
     *
     * @param request 原始请求
     *
     * @return 所有interfaceName
     */
    ServiceResult<List<String>> getInterfaces(DefaultCQE request);


    /**
     * 获取所有指定接口的方法
     *
     * @param request 接口名称
     *
     * @return 对应方法
     */
    ServiceResult<List<String>> getMethodNameByInterfaceName(GetMethodNameByInterfaceNameQuery request);


    /**
     * 初始化权限,如果权限不存在,则添加权限,如果权限已经存在,则不作任何修改
     *
     * @param request 权限集
     *
     * @return 添加的权限
     */
    ServiceResult<Integer> initPowerInProStart(DefaultCQE request) throws Exception;
}
