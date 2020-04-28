package indi.uhyils.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 游客id生成器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月27日 15时53分
 */
public class TouristUserIdMakeUtil {

    public static String getTouristUserId() {
        StringBuilder uid = new StringBuilder();
        //产生16位的强随机数
        Random rd = new SecureRandom();
        for (int i = 0; i < 16; i++) {
            //产生0-2的3位随机数
            int type = rd.nextInt(3);
            switch (type) {
                case 0:
                    //0-9的随机数
                    uid.append(rd.nextInt(10));
                    break;
                case 1:
                    //ASCII在65-90之间为大写,获取大写随机
                    uid.append((char) (rd.nextInt(25) + 65));
                    break;
                case 2:
                    //ASCII在97-122之间为小写，获取小写随机
                    uid.append((char) (rd.nextInt(25) + 97));
                    break;
                default:
                    break;
            }
        }
        return uid.toString();
    }
}
