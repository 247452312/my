package indi.uhyils.core.register;

/**
 * 可以使用ip和端口连接的
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月07日 08时36分
 */
public interface IpPortLinkable {


    /**
     * 获取ip
     *
     * @return
     */
    String getIp();

    /**
     * 获取端口
     *
     * @return
     */
    Integer getPort();

}
