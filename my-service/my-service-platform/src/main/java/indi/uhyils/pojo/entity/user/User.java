package indi.uhyils.pojo.entity.user;

import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.entity.base.BaseEntity;

/**
 * 对接平台用户entity
 * unique是用户名
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月23日 16时32分
 */
public interface User extends BaseEntity<String> {

    /**
     * 根据用户名查询用户
     *
     * @param userFacade
     */
    void findUserByUserName(UserFacade userFacade);

    /**
     * 每个协议分别检查密码是否正确
     *
     * @param userFacade
     * @param password
     *
     * @return
     */
    boolean checkPassword(UserFacade userFacade, String password);

    /**
     * 用户登录
     *
     * @param userFacade
     *
     * @return
     */
    boolean login(UserFacade userFacade);
}
