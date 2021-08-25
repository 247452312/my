package indi.uhyils.pojo.entity.type;

/**
 * 用户名
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时33分
 */
public class UserName implements BaseType {

    private String userName;

    public UserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
