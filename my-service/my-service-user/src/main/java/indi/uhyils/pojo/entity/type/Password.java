package indi.uhyils.pojo.entity.type;

import indi.uhyils.util.MD5Util;

/**
 * 密码
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时34分
 */
public class Password implements BaseType {

    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    /**
     * 转MD5编码
     *
     * @return
     */
    public String toMD5Str() {
        return MD5Util.MD5Encode(password);
    }
}
