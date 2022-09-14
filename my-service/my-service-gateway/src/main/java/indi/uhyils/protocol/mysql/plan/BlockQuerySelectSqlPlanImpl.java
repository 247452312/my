package indi.uhyils.protocol.mysql.plan;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import indi.uhyils.enums.InvokeTypeEnum;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.BlockQuerySelectSqlPlan;
import indi.uhyils.pojo.cqe.InvokeCommand;
import indi.uhyils.pojo.cqe.InvokeCommandBuilder;
import indi.uhyils.service.GatewaySdkService;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.SpringUtil;
import indi.uhyils.util.StringUtil;
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
    private final GatewaySdkService gatewaySdkService;


    public BlockQuerySelectSqlPlanImpl(SqlTableSourceBinaryTree froms, Map<String, String> headers, Map<String, Object> params) {
        super(froms, headers, params);
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
        if (name.startsWith("&")) {
            final Long resultIndex = Long.parseLong(name.substring(1));
            return lastAllPlanResult.get(resultIndex);
        }
        StringBuilder path = new StringBuilder();
        final String database = MysqlContent.MYSQL_TCP_INFO.get().getDatabase();
        if (owner != null) {
            path.append(owner);
            path.append(MysqlContent.PATH_SEPARATOR);
        } else if (StringUtil.isNotEmpty(database)) {
            path.append(database);
            path.append(MysqlContent.PATH_SEPARATOR);
        } else {
            Asserts.throwException("No database selected");
        }
        path.append(name);
        invokeCommandBuilder.addPath(path.toString());
        final InvokeCommand build = invokeCommandBuilder.build();
        return gatewaySdkService.invokeNode(build);
    }
}
