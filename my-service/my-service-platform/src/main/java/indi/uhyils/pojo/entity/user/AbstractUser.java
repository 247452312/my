package indi.uhyils.pojo.entity.user;

import indi.uhyils.context.UserContext;
import indi.uhyils.exception.UniqueException;
import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.request.FindUserByNameQuery;
import indi.uhyils.pojo.DTO.request.LoginCommand;
import indi.uhyils.pojo.DTO.response.LoginDTO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月23日 16时36分
 */
public abstract class AbstractUser implements User {

    /**
     * 用户名
     */
    private final String username;

    /**
     * 用户
     */
    protected UserDTO userDTO;

    protected AbstractUser(String username) {
        this.username = username;
    }

    @Override
    public String getUnique() {
        return username;
    }

    @Override
    public void setUnique(String unique) {
        throw new UniqueException();
    }

    @Override
    public void findUserByUserName(UserFacade userFacade) {
        this.userDTO = userFacade.getUserByUsername(FindUserByNameQuery.build(username));
    }

    @Override
    public boolean login(UserFacade userFacade) {
        // 调用系统权限登录
        LoginCommand loginCommand = LoginCommand.build(userDTO.getUsername(), userDTO.getPassword(), UserContext.MYSQL_ROLE_ID);
        LoginDTO loginDTO = userFacade.login(loginCommand);
        if (loginDTO != null) {
            // 生成的token缓存在mysqlHandler中
            String token = loginDTO.getToken();
            userDTO.setToken(token);
            doAfterLogin(userDTO);
            return true;
        }
        return false;
    }

    /**
     * 后续添加节点
     *
     * @param userDTO
     */
    protected abstract void doAfterLogin(UserDTO userDTO);
}
