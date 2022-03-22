package indi.uhyils.util.mysql.pojo.request.impl;

import indi.uhyils.protocol.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.util.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.util.mysql.pojo.response.MysqlResponse;
import indi.uhyils.util.mysql.pojo.response.impl.ErrResponse;
import java.util.Arrays;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComQuitRequest extends AbstractMysqlRequest {

    public ComQuitRequest(MysqlTcpInfo mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {
        // 既然是quit 就没啥好load的了
    }

    @Override
    public List<MysqlResponse> invoke() {
        return Arrays.asList(new ErrResponse(getMysqlHandler(),
                                             MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE,
                                             MysqlServerStatusEnum.SERVER_STATUS_IN_TRANS,
                                             "?? 你竟然想关掉我? 脑子瓦特了?\n" + MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE.getMsg()));
    }
}
