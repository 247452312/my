package indi.uhyils.util;

import indi.uhyils.content.Content;

import java.util.Random;

/**
 * 本项目id生产规则
 *
 * long类型共计8字节64位,最高位恒为0代表正数,第1位到第43位存储时间戳,第44位到底48位(5位)代表分布式节点index,49-58位为10位随机数(防重),59-64位预留,等待其他业务需要
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月06日 19时27分
 */
public class IdUtil {


    private static final Random RANDOM_UTIL = new Random();

    private static long newId() {
        // 生成时间
        long time = System.currentTimeMillis();
        long timeResult = (time & Content.TIME_MASK) << Content.TIME_DISPLACEMENT;

        // 此处随机生成, 但是不应该是随机生成,而是从分布式管理中心获取
        long distributed = 0b10001L;
        long distributedResult = (distributed & Content.DISTRIBUTED_MASK) << Content.DISTRIBUTED_DISPLACEMENT;

        // 生成 随机数
        long random = RANDOM_UTIL.nextInt(Content.RANDOM_MASK.intValue());
        long randomResult = random << Content.RANDOM_DISPLACEMENT;

        return timeResult | distributedResult | randomResult;
    }
}
