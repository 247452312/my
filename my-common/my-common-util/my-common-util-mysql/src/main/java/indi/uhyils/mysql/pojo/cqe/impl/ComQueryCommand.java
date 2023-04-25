package indi.uhyils.mysql.pojo.cqe.impl;

import indi.uhyils.mysql.decode.Proto;
import indi.uhyils.mysql.enums.MysqlCommandTypeEnum;
import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.enums.SqlTypeEnum;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.mysql.pojo.response.impl.OkResponse;
import indi.uhyils.mysql.pojo.response.impl.ResultSetResponse;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.PlanInvoker;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComQueryCommand extends MysqlSqlCommand {

    private String completeSql;

    public ComQueryCommand(MysqlThisRequestInfo mysqlThisRequestInfo, String sql) {
        super(mysqlThisRequestInfo);
        this.completeSql = sql;
    }

    public ComQueryCommand(MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlThisRequestInfo);
    }

    @Override
    public List<MysqlResponse> invoke() throws Exception {
        if (StringUtil.isEmpty(completeSql)) {
            return Collections.singletonList(new ErrResponse(MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "sql语句不能为空"));
        }
        LogUtil.info("mysql协议sql执行了:" + completeSql);
        final String[] split = completeSql.split(";");
        List<MysqlResponse> result = new ArrayList<>();
        for (String sql : split) {
            // 解析sql为执行计划
            final List<MysqlPlan> mysqlPlans = MysqlUtil.analysisSqlToPlan(sql);
            // 执行计划为空, 返回执行成功,无信息
            if (CollectionUtil.isEmpty(mysqlPlans)) {
                final List<OkResponse> okResponses = Collections.singletonList(new OkResponse(SqlTypeEnum.NULL));
                result.addAll(okResponses);
                continue;
            }

            final NodeInvokeResult execute = new PlanInvoker(mysqlPlans).execute();

            // 如果没有结果, 说明不是一个常规的查询语句,返回ok即可,如果报错,则在外部已经进行了try,catch
            if (CollectionUtil.isEmpty(execute.getFieldInfos())) {
                result.add(new OkResponse(SqlTypeEnum.NULL));
                continue;
            }
            result.add(new ResultSetResponse(execute.getFieldInfos(), execute.getResult()));
        }
        return result;
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_QUERY;
    }

    @Override
    protected void load() {
        byte[] mysqlBytes = mysqlThisRequestInfo.getMysqlBytes();
        Proto proto = new Proto(mysqlBytes, 5);
        this.completeSql = proto.get_null_str();
    }
}
