package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import java.util.List;

/**
 * 工单节点样例表(OrderBaseNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时58分57秒
 */
public class OrderBaseNode extends AbstractDoEntity<OrderBaseNodeDO> {

    /**
     * 节点属性
     */
    private List<OrderBaseNodeField> fields;

    /**
     * 节点结果类型
     */
    private List<OrderBaseNodeResultType> resultTypes;

    /**
     * 节点结果路由
     */
    private List<OrderBaseNodeRoute> routes;

    @Default
    public OrderBaseNode(OrderBaseNodeDO data) {
        super(data);
    }

    public OrderBaseNode(Long id) {
        super(id, new OrderBaseNodeDO());
    }

    public List<OrderBaseNodeField> fields() {
        return fields;
    }

    public List<OrderBaseNodeRoute> routes() {
        return routes;
    }

    public List<OrderBaseNodeResultType> resultTypes() {
        return resultTypes;
    }

    public void fillFields(List<OrderBaseNodeField> fields) {
        if (this.fields != null) {
            return;
        }
        forceFillFields(fields);
    }

    public void forceFillFields(List<OrderBaseNodeField> fields) {
        this.fields = fields;
    }

    public void fillRoutes(List<OrderBaseNodeRoute> routes) {
        if (this.routes != null) {
            return;
        }
        forceFillRoutes(routes);
    }

    public void forceFillRoutes(List<OrderBaseNodeRoute> routes) {
        this.routes = routes;
    }

    public void fillResultTypes(List<OrderBaseNodeResultType> resultTypes) {
        if (this.resultTypes != null) {
            return;
        }
        forceFillResultTypes(resultTypes);
    }

    public void forceFillResultTypes(List<OrderBaseNodeResultType> resultTypes) {
        this.resultTypes = resultTypes;
    }


}
