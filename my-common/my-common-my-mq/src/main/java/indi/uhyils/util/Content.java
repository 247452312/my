package indi.uhyils.util;

/**
 * 程序中用到的常量
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月10日 07时25分
 */
public interface Content {

    /*生产规则*/
    /*时间start*/
    /**
     * 时间位数
     */
    Long TIME_BIT = 43L;
    /**
     * 时间位移(最高位始终为0代表正数)
     */
    Long TIME_DISPLACEMENT = Long.bitCount(Long.MAX_VALUE) - TIME_BIT;
    /**
     * 时间掩码
     */
    Long TIME_MASK = (1L << TIME_BIT) - 1L;
    /*时间end*/

    /*序列位start*/
    /**
     * 序列位位数
     */
    Long SEQUENCE_BIT = 10L;
    /**
     * 序列位位移
     */
    Long SEQUENCE_DISPLACEMENT = TIME_DISPLACEMENT - SEQUENCE_BIT;
    /**
     * 序列位掩码
     */
    Long SEQUENCE_MASK = (1L << SEQUENCE_BIT) - 1L;
    /*序列位end*/

    /*分布式编码start*/
    /**
     * 分布式编码位数
     */
    Long DISTRIBUTED_BIT = 5L;
    /**
     * 分布式编码位移
     */
    Long DISTRIBUTED_DISPLACEMENT = SEQUENCE_DISPLACEMENT - DISTRIBUTED_BIT;
    /**
     * 分布式编码掩码
     */
    Long DISTRIBUTED_MASK = (1L << DISTRIBUTED_BIT) - 1L;
    /*分布式编码end*/


    /*预留5位其他业务,请自行添加*/

}
