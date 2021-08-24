package indi.uhyils.entity.type;

import indi.uhyils.util.AESUtil;
import indi.uhyils.util.SpringUtil;

/**
 * token本身
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 18时26分
 */
public class Token implements BaseType {

    /**
     * 这个token对应的id
     */
    private final Long id;

    /**
     * token本身
     */
    private final String token;

    public Token(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public Token(String token) {
        this.token = token;
        this.id = parseTokenToId(token);
    }

    public Long getId() {
        return id;
    }

    private Long parseTokenToId(String token) {
        String salt = SpringUtil.getProperty("token.salt");
        assert salt != null;
        String encodeRules = SpringUtil.getProperty("token.encodeRules");
        assert encodeRules != null;
        String tokenInfoString = AESUtil.AESDecode(encodeRules, token);
        assert tokenInfoString != null;
        return Long.parseLong(tokenInfoString.substring(10, tokenInfoString.length() - 1 - salt.length()));
    }

    public String getToken() {
        return token;
    }
}
