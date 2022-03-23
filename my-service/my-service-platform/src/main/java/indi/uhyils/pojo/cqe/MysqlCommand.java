package indi.uhyils.pojo.cqe;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;
import java.util.List;

/**
 * mysql客户端的请求
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 09时38分
 */
public interface MysqlCommand {


    /**
     * 执行
     *
     * @return 执行后的返回
     */
    List<MysqlResponse> invoke() throws Exception;


    /**
     * 加载客户端请求
     *
     * @param mysqlThisRequestInfo
     */
    void load(MysqlThisRequestInfo mysqlThisRequestInfo);

    /**
     * 类型
     *
     * @return
     */
    MysqlCommandTypeEnum type();

}
