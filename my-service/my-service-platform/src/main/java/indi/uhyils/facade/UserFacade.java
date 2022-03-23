package indi.uhyils.facade;

import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.request.FindUserByNameQuery;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.response.LoginDTO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月07日 08时56分
 */
public interface UserFacade extends BaseFacade {

    /**
     * 使用传入的用户名密码强制登录
     *
     * @param loginCommand
     *
     * @return
     */
    LoginDTO login(LoginCommand loginCommand);

    /**
     * 根据用户名获取用户
     *
     * @param query
     *
     * @return
     */
    UserDTO getUserByUsername(FindUserByNameQuery query);
}
