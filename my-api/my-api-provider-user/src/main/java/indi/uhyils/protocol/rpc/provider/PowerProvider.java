package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.PowerDO;
import indi.uhyils.pojo.DTO.request.CheckUserHavePowerRequest;
import indi.uhyils.pojo.DTO.request.GetMethodNameByInterfaceNameRequest;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.DefaultDTOProvider;
import java.util.ArrayList;

/**
 * 权限接口API
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface PowerProvider extends DefaultDTOProvider<PowerDO> {

    /**
     * 获取所有的权限
     *
     * @param request 请求
     *
     * @return 所有权限
     */
    ServiceResult<ArrayList<PowerDO>> getPowers(DefaultRequest request);

    /**
     * 检查用户是否存在此权限
     *
     * @param request 检查用户是否存在此权限请求
     *
     * @return 是否存在
     */
    ServiceResult<Boolean> checkUserHavePower(CheckUserHavePowerRequest request);

    /**
     * 删除权限->包括连接表
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> deletePower(IdRequest request);


    /**
     * 获取所有interfaceName
     *
     * @param request 原始请求
     *
     * @return 所有interfaceName
     */
    ServiceResult<ArrayList<String>> getInterfaces(DefaultRequest request);


    /**
     * 获取所有指定接口的方法
     *
     * @param request 接口名称
     *
     * @return 对应方法
     */
    ServiceResult<ArrayList<String>> getMethodNameByInterfaceName(GetMethodNameByInterfaceNameRequest request);


    /**
     * 初始化权限,如果权限不存在,则添加权限,如果权限已经存在,则不作任何修改
     *
     * @param request 权限集
     *
     * @return 添加的权限
     */
    ServiceResult<Integer> initPowerInProStart(DefaultRequest request) throws Exception;
}
