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
import indi.uhyils.protocol.mysql.handler.MysqlTcpInfo;
import indi.uhyils.repository.PlatformInternalNodeRepository;
import indi.uhyils.repository.PlatformPublishNodeRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * mysql的tcp连接信息
     */
    private final MysqlTcpInfo mysqlTcpInfo;

    /**
     * 执行计划
     */
    private List<MysqlPlan> plans;

    /**
     * 执行结果
     */
    private JSONArray result;

    public VirtualNodeImpl(String sql, MysqlTcpInfo mysqlTcpInfo) {
        this.sql = new Sql(sql);
        this.mysqlTcpInfo = mysqlTcpInfo;
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

        // <index, 结果集>
        Map<Long, JSONArray> resultMap = new HashMap<>(plans.size());
        List<FieldInfo> fieldInfos = new ArrayList<>();

        // 执行执行计划
        for (MysqlPlan mysqlPlan : plans) {
            mysqlPlan.initNode(publishNodeRepository, internalNodeRepository);
            fieldInfos.addAll(mysqlPlan.colInfos());
            JSONArray result = mysqlPlan.invoke(resultMap);
            resultMap.put(mysqlTcpInfo.index(), result);
            mysqlTcpInfo.addIndex();
        }

        // 组合结果
        JSONArray result = new JSONArray();

        return NodeInvokeResult.build(fieldInfos, result);

    }


}
