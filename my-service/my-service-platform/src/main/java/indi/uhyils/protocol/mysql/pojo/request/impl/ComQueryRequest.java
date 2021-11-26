package indi.uhyils.protocol.mysql.pojo.request.impl;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.protocol.mysql.decoder.impl.Proto;
import indi.uhyils.protocol.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.enums.SqlTypeEnum;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.plan.MysqlPlan;
import indi.uhyils.protocol.mysql.plan.PlanUtil;
import indi.uhyils.protocol.mysql.pojo.entity.FieldInfo;
import indi.uhyils.protocol.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.OkResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.ResultSetResponse;
import indi.uhyils.util.CollectionUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComQueryRequest extends AbstractMysqlRequest {

    private String sql;

    public ComQueryRequest(MysqlHandler mysqlHandler, String sql) {
        super(mysqlHandler);
        this.sql = sql;
    }

    public ComQueryRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlBytes, 4);
        this.sql = proto.get_null_str();
    }

    @Override
    public List<MysqlResponse> invoke() throws Exception {
        if (StringUtils.isBlank(sql)) {
            return Collections
                .singletonList(new ErrResponse(getMysqlHandler(), MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "sql语句不能为空"));
        }

        // 解析sql为执行计划
        List<MysqlPlan> mysqlPlans = PlanUtil.parseSqlToPlan(sql);
        // 执行计划为空, 返回执行成功,无信息
        if (CollectionUtil.isEmpty(mysqlPlans)) {
            return Collections
                .singletonList(new OkResponse(getMysqlHandler(), SqlTypeEnum.NULL));
        }

        // index, 结果集
        Map<Long, JSONArray> result = new HashMap<>(mysqlPlans.size());
        // 此sql最终结果
        JSONArray invokeResult = null;
        // 此sql的最终列信息
        List<FieldInfo> colInfos = null;
        // 执行执行计划
        for (MysqlPlan mysqlPlan : mysqlPlans) {
            JSONArray rr = mysqlPlan.invoke(result);
            invokeResult = rr;
            colInfos = mysqlPlan.colInfos();
            result.put(mysqlPlan.index(), rr);
        }
        // 如果没有结果, 说明不是一个常规的查询语句,返回ok即可,如果报错,则在外部已经进行了try,catch
        if (CollectionUtil.isEmpty(colInfos)) {
            return Collections
                .singletonList(new OkResponse(getMysqlHandler(), SqlTypeEnum.NULL));
        }
        return Collections.singletonList(new ResultSetResponse(getMysqlHandler(), colInfos, invokeResult));

    }
}
