package indi.uhyils.pojo.cqe.impl;

import indi.uhyils.enums.MysqlCommandTypeEnum;
import indi.uhyils.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.decode.Proto;
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.protocol.mysql.handler.MysqlThisRequestInfo;
import java.util.List;


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
    protected void load() {
        byte[] mysqlBytes = mysqlThisRequestInfo.getMysqlBytes();
        Proto proto = new Proto(mysqlBytes, 4);
        this.sql = proto.get_null_str();
    }

    @Override
    public List<MysqlResponse> invoke() throws Exception {
//        if (StringUtils.isBlank(sql)) {
//            return Collections
//                .singletonList(new ErrResponse(getMysqlTcpInfo(), MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "sql语句不能为空"));
//        }
//
//        // 解析sql为执行计划
//        List<MysqlPlan> mysqlPlans = PlanUtil.parseSqlToPlan(sql);
//        // 执行计划为空, 返回执行成功,无信息
//        if (CollectionUtil.isEmpty(mysqlPlans)) {
//            return Collections
//                .singletonList(new OkResponse(getMysqlTcpInfo(), SqlTypeEnum.NULL));
//        }
//
//        // index, 结果集
//        Map<Long, JSONArray> result = new HashMap<>(mysqlPlans.size());
//        // 此sql最终结果
//        JSONArray invokeResult = null;
//        // 此sql的最终列信息
//        List<FieldInfo> colInfos = null;
//        // 执行执行计划
//        for (MysqlPlan mysqlPlan : mysqlPlans) {
//            JSONArray rr = mysqlPlan.invoke(result);
//            invokeResult = rr;
//            colInfos = mysqlPlan.colInfos();
//            result.put(mysqlPlan.index(), rr);
//        }
//        // 如果没有结果, 说明不是一个常规的查询语句,返回ok即可,如果报错,则在外部已经进行了try,catch
//        if (CollectionUtil.isEmpty(colInfos)) {
//            return Collections
//                .singletonList(new OkResponse(mysqlTcpInfo, SqlTypeEnum.NULL));
//        }
//        return Collections.singletonList(new ResultSetResponse(mysqlTcpInfo, colInfos, invokeResult));

        return null;

    }

    @Override
    public MysqlCommandTypeEnum type() {
        return MysqlCommandTypeEnum.COM_QUERY;
    }
}
