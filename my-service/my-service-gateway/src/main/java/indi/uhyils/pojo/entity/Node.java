package indi.uhyils.pojo.entity;

import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import indi.uhyils.annotation.Default;
import indi.uhyils.exception.AssertException;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.PlanInvoker;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.BlockQuerySelectSqlPlan;
import indi.uhyils.pojo.DO.NodeDO;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 转换节点表(Node)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class Node extends AbstractDataNode<NodeDO> {

    /**
     * 此节点对应的执行计划
     */
    private List<MysqlPlan> plans;


    @Default
    public Node(NodeDO data) {
        super(data);
    }

    public Node(Long id) {
        super(id, new NodeDO());
    }

    @Override
    public String databaseName() {
        return toDataAndValidate().getDatabase();
    }

    @Override
    public String tableName() {
        return toDataAndValidate().getTableName();
    }

    /**
     * 填充子节点
     *
     * @param nodeRepository
     */
    @Override
    public void fill(NodeRepository nodeRepository, ProviderInterfaceRepository providerInterfaceRepository) {
        String sql = toDataAndValidate().getSql();
        // 解析sql为执行计划
        List<MysqlPlan> mysqlPlans = MysqlUtil.analysisSqlToPlan(sql);

        List<MysqlPlan> result = new ArrayList<>();
        // 将blockSql 执行计划 转换为node执行计划
        for (MysqlPlan plan : mysqlPlans) {
            if (plan instanceof BlockQuerySelectSqlPlan) {
                BlockQuerySelectSqlPlan selectSqlPlan = (BlockQuerySelectSqlPlan) plan;
                SqlTableSourceBinaryTree sqlTableSourceBinaryTree = selectSqlPlan.toTable();
                SQLPropertyExpr tableSource = sqlTableSourceBinaryTree.getTableSource();
                String ownerName = tableSource.getOwnernName();
                String name = tableSource.getName();
                AbstractDataNode abstractDataNode = nodeRepository.findNodeOrProvider(ownerName, name);
                abstractDataNode.fill(nodeRepository, providerInterfaceRepository);
                MysqlPlan nodeInvokePlan = new MysqlNodeInvokePlan(abstractDataNode, selectSqlPlan, sql);
                result.add(nodeInvokePlan);
                continue;
            }
            result.add(plan);
        }
        this.plans = result;
    }

    @Override
    public NodeInvokeResult getResult(Map<String, String> header, Map<String, Object> params) {
        NodeInvokeResult execute = null;
        try {
            execute = new PlanInvoker(plans).execute();
        } catch (AssertException e) {
            LogUtil.error(e);
        }
        return execute;
    }
}
