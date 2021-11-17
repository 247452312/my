package indi.uhyils.protocol.mysql.pojo.request;

import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;
import java.util.List;

/**
 * mysql客户端的请求
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 09时38分
 */
public interface MysqlRequest {


    /**
     * 执行
     *
     * @return 执行后的返回
     */
    List<MysqlResponse> invoke();


    /**
     * 加载客户端请求
     *
     * @param mysqlBytes
     */
    void load(byte[] mysqlBytes);

}
