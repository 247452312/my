package indi.uhyils.pojo.entity;

import indi.uhyils.context.MyContext;
import indi.uhyils.pojo.DO.base.TokenInfo;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.UserRepository;
import indi.uhyils.util.AESUtil;
import indi.uhyils.util.SpringUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * token本身
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 18时26分
 */
public class Token extends AbstractEntity {

    /**
     * 这个token对应的id
     */
    private final Identifier id;

    /**
     * token本身
     */
    private final String token;

    public Token(Long id, String token) {
        this.id = new Identifier(id);
        this.token = token;
    }

    public Token(String token) {
        this.token = token;
        this.id = parseTokenToId(token);
    }

    @Override
    public Identifier getId() {
        return id;
    }

    private Identifier parseTokenToId(String token) {
        String salt = SpringUtil.getProperty("token.salt");
        assert salt != null;
        String encodeRules = SpringUtil.getProperty("token.encodeRules");
        assert encodeRules != null;
        String tokenInfoString = AESUtil.AESDecode(encodeRules, token);
        assert tokenInfoString != null;
        long id = Long.parseLong(tokenInfoString.substring(10, tokenInfoString.length() - 1 - salt.length()));
        return new Identifier(id);
    }

    public String getToken() {
        return token;
    }

    public TokenInfo parseToTokenInfo(String encodeRules, String salt, UserRepository userRepository) {
        String tokenInfoString = AESUtil.AESDecode(encodeRules, token);

        assert tokenInfoString != null;
        String day = tokenInfoString.substring(0, 2);
        String hour = tokenInfoString.substring(2, 4);
        String mon = tokenInfoString.substring(4, 6);
        String sec = tokenInfoString.substring(6, 8);
        String random = tokenInfoString.substring(8, 10);

        long userId = Long.parseLong(tokenInfoString.substring(10, tokenInfoString.length() - 1 - salt.length()));

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setDay(Integer.parseInt(day));
        tokenInfo.setHour(Integer.parseInt(hour));
        tokenInfo.setMin(Integer.parseInt(mon));
        tokenInfo.setSec(Integer.parseInt(sec));
        tokenInfo.setRandom(Integer.parseInt(random));
        tokenInfo.setUserId(userId);
        Boolean aBoolean = userRepository.haveToken(this);
        if (aBoolean == null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddhhmm");
            String format = localDateTime.format(dateTimeFormatter);
            int dayNow = Integer.parseInt(format.substring(0, 2));
            int hourNow = Integer.parseInt(format.substring(2, 4));
            int monNow = Integer.parseInt(format.substring(4, 6));
            // 如果分钟差超过30
            if (monNow - Integer.parseInt(mon) >= MyContext.LOGIN_TIME_OUT_MIN) {
                tokenInfo.setTimeOut(Boolean.TRUE);
            } else if (hourNow - Integer.parseInt(hour) > 0) {
                tokenInfo.setTimeOut(Boolean.TRUE);
            } else if (dayNow - Integer.parseInt(day) > 0) {
                tokenInfo.setTimeOut(Boolean.TRUE);
            } else {
                tokenInfo.setTimeOut(false);
            }
        } else {
            tokenInfo.setTimeOut(!aBoolean);
        }
        return tokenInfo;
    }
}
