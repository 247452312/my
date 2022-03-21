package indi.uhyils.service;

import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.FindUserByNameQuery;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.response.LoginDTO;
import indi.uhyils.pojo.cqe.InvokeCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月21日 08时46分
 */
public interface MysqlService {

    /**
     * 根据一个消费者名称获取对应的信息
     *
     * @param query
     *
     * @return
     */
    ServiceResult<UserDTO> findPasswordByName(FindUserByNameQuery query);

    /**
     * 执行执行计划
     * 因为此接口是供给外层(mysql,rpc,http)调用使用的. 所以调用的节点一定是发布节点
     *
     * @param command
     *
     * @return
     */
    ServiceResult<InvokeResponse> invoke(InvokeCommand command) throws Exception;

    /**
     * Mysql协议的强制登录,无需校验密码的那种
     *
     * @param loginCommand
     *
     * @return
     */
    ServiceResult<LoginDTO> login(LoginCommand loginCommand);

}
