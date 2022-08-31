package indi.uhyils.mysql.pojo.cqe.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.mysql.decode.Proto;
import indi.uhyils.mysql.enums.MysqlCommandTypeEnum;
import indi.uhyils.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.mysql.enums.SqlTypeEnum;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.handler.MysqlThisRequestInfo;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.pojo.response.MysqlResponse;
import indi.uhyils.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.mysql.pojo.response.impl.OkResponse;
import indi.uhyils.mysql.pojo.response.impl.ResultSetResponse;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.PlanUtil;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.StringUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComQueryCommand extends MysqlSqlCommand {

    private String sql;

    public ComQueryCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo, String sql) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
        this.sql = sql;
    }

    public ComQueryCommand(MysqlTcpInfo mysqlTcpInfo, MysqlThisRequestInfo mysqlThisRequestInfo) {
        super(mysqlTcpInfo, mysqlThisRequestInfo);
    }

    @Override
    public List<MysqlResponse> invoke() throws Exception {
        if (StringUtil.isEmpty(sql)) {
            return Collections.singletonList(new ErrResponse(getMysqlTcpInfo(), MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "sql语句不能为空"));
        }

        // 解析sql为执行计划
        final List<MysqlPlan> mysqlPlans = MysqlUtil.analysisSqlToPlan(sql, new HashMap<>());
        // 执行计划为空, 返回执行成功,无信息
        if (CollectionUtil.isEmpty(mysqlPlans)) {
            return Collections.singletonList(new OkResponse(getMysqlTcpInfo(), SqlTypeEnum.NULL));
        }

        final NodeInvokeResult execute = PlanUtil.execute(mysqlPlans, new HashMap<>());

        // 如果没有结果, 说明不是一个常规的查询语句,返回ok即可,如果报错,则在外部已经进行了try,catch
        if (CollectionUtil.isEmpty(execute.getFieldInfos())) {
            return Collections.singletonList(new OkResponse(mysqlTcpInfo, SqlTypeEnum.NULL));
        }

        final List<Map<String, Object>> collect = execute.getJsonArray().stream().map(t -> JSONObject.parseObject(JSON.toJSONString(t))).map(t -> (Map<String, Object>) t).collect(Collectors.toList());
        return Collections.singletonList(new ResultSetResponse(mysqlTcpInfo, execute.getFieldInfos(), collect));
    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_QUERY;
    }

    @Override
    protected void load() {
        byte[] mysqlBytes = mysqlThisRequestInfo.getMysqlBytes();
        Proto proto = new Proto(mysqlBytes, 5);
        this.sql = proto.get_null_str();
    }
}
