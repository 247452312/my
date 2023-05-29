package indi.uhyils.mysql.pojo.cqe.impl;

import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.enums.MysqlCommandTypeEnum;
import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlHandlerStatusEnum;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.pojo.cqe.AbstractMysqlCommand;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.pojo.response.impl.ErrResponse;
import java.util.Arrays;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComQuitCommand extends AbstractMysqlCommand {

    public ComQuitCommand(MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlThisRequestInfo);
    }

    @Override
    public List<MysqlResponse> invoke() {
        MysqlContent.MYSQL_TCP_INFO.get().setStatus(MysqlHandlerStatusEnum.OVER);
        return Arrays.asList(ErrResponse.build("?? 你竟然想关掉我? 脑子瓦特了?\n" + MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE.getMsg()));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_QUIT;
    }

    @Override
    protected void load() {
        // 既然是quit 就没啥好load的了
    }
}
