package indi.uhyils.pojo.request.impl;

import indi.uhyils.handler.MysqlHandler;
import indi.uhyils.pojo.request.AbstractMysqlRequest;
import indi.uhyils.pojo.response.MysqlResponse;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时47分
 */
public class ComStmtSendLongDataRequest extends AbstractMysqlRequest {

    public ComStmtSendLongDataRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {

    }

    @Override
    public List<MysqlResponse> invoke() {
        return null;
    }
}