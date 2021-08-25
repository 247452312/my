package indi.uhyils.pojo.entity.type;

import indi.uhyils.util.AESUtil;
import indi.uhyils.util.AssertUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 用户id
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 18时25分
 */
public class UserId extends Identifier {

    public UserId(Long id) {
        super(id);
        AssertUtil.assertTrue(id != null, "用户id为空");
    }

    public Token toToken(String salt,String encodeRules) {
        StringBuilder sb = new StringBuilder(26 + salt.length());

        //生成日期部分 8位
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddhhmmss");
        String format = localDateTime.format(dateTimeFormatter);
        sb.append(format);
        Random random = new Random(90);
        int randomNum = random.nextInt() + 10;
        //两位随机数 两位
        sb.append(randomNum);

        // 用户id 19位
        String str = getId().toString();
        long i = 19L - str.length();
        // long 最大19位 如果不够 最高位补0
        StringBuilder sbTemp = new StringBuilder(19);
        for (int j = 0; j < i; j++) {
            sbTemp.append("0");
        }
        sbTemp.append(str);
        sb.append(sbTemp);
        //盐 x位
        sb.append(salt);

        return new Token(getId(), AESUtil.AESEncode(encodeRules, sb.toString()));
    }
}
