package indi.uhyils.pojo.entity;

/**
 * 完备用户,包含用户本身,完备角色
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时09分
 */
public class CompleteUser extends AbstractEntity {

    private User user;

    private CompleteRole role;

    public CompleteUser(User user, CompleteRole role) {
        this.user = user;
        this.role = role;
    }
}
