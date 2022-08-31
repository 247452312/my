package indi.uhyils.protocol.mysql.plan;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import indi.uhyils.enums.InvokeTypeEnum;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.BlockQuerySelectSqlPlan;
import indi.uhyils.pojo.cqe.InvokeCommand;
import indi.uhyils.pojo.cqe.InvokeCommandBuilder;
import indi.uhyils.service.GatewaySdkService;
import indi.uhyils.util.SpringUtil;
import java.util.List;
import java.util.Map;

/**
 * 简单sql执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时31分
 */
public class BlockQuerySelectSqlPlanImpl extends BlockQuerySelectSqlPlan {

    /**
     * 节点
     */
    private GatewaySdkService gatewaySdkService;


    public BlockQuerySelectSqlPlanImpl(List<MysqlPlan> mysqlPlan, SqlTableSourceBinaryTree froms, Map<String, String> headers, Map<String, Object> params) {
        super(mysqlPlan, froms, headers, params);
        this.gatewaySdkService = SpringUtil.getBean(GatewaySdkService.class);
    }

    @Override
    public NodeInvokeResult invoke() {
        InvokeCommandBuilder invokeCommandBuilder = new InvokeCommandBuilder();
        invokeCommandBuilder.setType(InvokeTypeEnum.MYSQL.getCode());
        invokeCommandBuilder.addArgs(params);
        invokeCommandBuilder.addHeader(headers);
        final SQLPropertyExpr tableSource = froms.getTableSource();
        final SQLExpr owner = tableSource.getOwner();
        final String name = tableSource.getName();
        StringBuilder path = new StringBuilder();
        if (owner == null) {
            path.append(owner.toString());
            path.append(MysqlContent.PATH_SEPARATOR);
        }
        path.append(name);
        invokeCommandBuilder.addPath(path.toString());
        final InvokeCommand build = invokeCommandBuilder.build();
        return gatewaySdkService.invokeNode(build);
    }
}
