package indi.uhyils.pojo.entity;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.fastjson.JSONArray;
import indi.uhyils.interpreter.Interpreter;
import indi.uhyils.pojo.DTO.FieldInfo;
import indi.uhyils.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.entity.node.Sql;
import indi.uhyils.pojo.entity.node.VirtualNode;
import indi.uhyils.pojo.entity.plan.MysqlPlan;
import indi.uhyils.repository.PlatformInternalNodeRepository;
import indi.uhyils.repository.PlatformPublishNodeRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.SpringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月21日 09时52分
 */
public class VirtualNodeImpl implements VirtualNode {


    /**
     * 节点sql
     */
    private final Sql sql;

    /**
     * 执行计划
     */
    private List<MysqlPlan> plans;

    /**
     * 执行结果
     */
    private JSONArray result;

    public VirtualNodeImpl(String sql) {
        this.sql = new Sql(sql);
    }

    public void analysisSqlToPlan(PlatformPublishNodeRepository publishNodeRepository, PlatformInternalNodeRepository internalNodeRepository) {
        String realSql = sql.replaceRealSql(null);
        plans = analysisSqlToPlan(publishNodeRepository, internalNodeRepository, realSql);
    }

    /**
     * 解析sql为执行计划
     *
     * @param publishNodeRepository
     * @param internalNodeRepository
     * @param realSql
     *
     * @return
     */
    private List<MysqlPlan> analysisSqlToPlan(PlatformPublishNodeRepository publishNodeRepository, PlatformInternalNodeRepository internalNodeRepository, String realSql) {
        SQLStatement sqlStatement = new MySqlStatementParser(realSql).parseStatement();
        List<Interpreter> beans = SpringUtil.getBeans(Interpreter.class);
        for (Interpreter bean : beans) {
            if (bean.canParse(sqlStatement)) {
                return bean.parse(sqlStatement);
            }
        }
        Asserts.assertTrue(false, "解析执行计划失败:{}", sql);
        return null;
    }


    @Override
    public NodeInvokeResult invoke(PlatformPublishNodeRepository publishNodeRepository, PlatformInternalNodeRepository internalNodeRepository) {
        // 解析sql为执行计划
        analysisSqlToPlan(publishNodeRepository, internalNodeRepository);
        if (CollectionUtil.isEmpty(plans)) {
            return null;
        }

        // index, 结果集
        Map<Long, JSONArray> resultMap = new HashMap<>(plans.size());
        // 此sql最终结果
        JSONArray invokeResult = null;
        // 此sql的最终列信息
        List<FieldInfo> colInfos = null;
        // 执行执行计划
        for (MysqlPlan mysqlPlan : plans) {
            JSONArray result = mysqlPlan.invoke(resultMap);
//            invokeResult = result;
//            colInfos = mysqlPlan.colInfos();
            resultMap.put(mysqlPlan.index(), result);
        }
        List<Object> collect = resultMap.entrySet().stream().flatMap(t -> t.getValue().stream()).collect(Collectors.toList());
        return new JSONArray(collect);

    }

    @Override
    public List<FieldInfo> getFieldInfo() {
        return null;
    }

}
