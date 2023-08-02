package indi.uhyils.pojo.entity;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.mysql.pojo.entity.DataNode;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import java.util.List;
import java.util.Map;

/**
 * 数据处理节点模板
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月30日 09时12分
 */
public abstract class AbstractDataNode<T extends BaseDO> extends AbstractDoEntity<T> implements DataNode {

    /**
     * 此节点的入参
     */
    protected Map<String, Object> params;

    /**
     * 此节点的请求头信息
     */
    protected Map<String, String> header;

    /**
     * 此节点对应的sql语句
     */
    protected String sql;

    public AbstractDataNode(T t) {
        super(t);
    }

    public AbstractDataNode(Long id, T t) {
        super(id, t);
    }

    @Override
    public NodeInvokeResult getResult() {
        return new NodeInvokeResult(null);
    }


    /**
     * 填充sql信息
     *
     * @param sql
     */
    public void fillSqlInfo(String sql, Map<String, String> headers, Map<String, Object> params) {
        this.sql = sql;
        this.header = headers;
        this.params = params;
    }
}
