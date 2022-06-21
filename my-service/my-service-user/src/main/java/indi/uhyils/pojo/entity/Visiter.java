package indi.uhyils.pojo.entity;

import indi.uhyils.enums.UserTypeEnum;
import indi.uhyils.pojo.DO.UserDO;
import indi.uhyils.repository.UserRepository;
import indi.uhyils.util.AESUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月21日 08时54分
 */
public class Visiter extends User {

    /**
     * 游客访问的ip
     */
    private String ip;

    public Visiter(UserDO data) {
        super(data);
    }

    public Visiter(String ip) {
        super(new UserDO());
        this.ip = ip;
    }

    @Override
    public User login(UserRepository userRepository, String salt, String encodeRole) {
        this.token = toToken(salt, encodeRole);
        return this;
    }

    @Override
    public Token toToken(String salt, String encodeRules) {
        StringBuilder sb = new StringBuilder(26 + salt.length());

        // 用户类型 2位
        sb.append(UserTypeEnum.VISITER.getCode());

        //生成日期部分 8位
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddhhmmss");
        String format = localDateTime.format(dateTimeFormatter);
        sb.append(format);
        Random random = new Random(90);
        int randomNum = random.nextInt() + 10;
        //两位随机数 两位
        sb.append(randomNum);

        // 用户ip 17位
        long i = 17L - ip.length();
        // long 最大17位 如果不够 最高位补0
        StringBuilder sbTemp = new StringBuilder(17);
        for (int j = 0; j < i; j++) {
            sbTemp.append("0");
        }
        sbTemp.append(ip);
        sb.append(sbTemp);
        //盐 x位
        sb.append(salt);

        return new Token(getUnique().getId(), AESUtil.AESEncode(encodeRules, sb.toString()));
    }
}
