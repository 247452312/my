package indi.uhyils.protocol.mysql.pojo.response;

/**
 * mysql协议返回类型
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 09时40分
 */
public interface MysqlResponse {


    /**
     * 将自身转化为要返回的类型
     *
     * @return
     */
    byte[] toByte();


    /**
     * 获取响应报文的首字符
     *
     * @return
     */
    byte getFirstByte();


    /**
     * 返回没有前面长度位或标志位的字节组
     *
     * @return
     */
    byte[] toByteNoMarkIndex();


}
