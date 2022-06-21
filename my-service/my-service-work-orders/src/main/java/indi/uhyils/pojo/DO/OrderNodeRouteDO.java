package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 节点间关联路由样例表(OrderNodeRoute)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月11日 10时46分09秒
 */
@TableName(value = "sys_order_node_route")
public class OrderNodeRouteDO extends BaseDO {

    private static final long serialVersionUID = -17596176587193657L;


    /**
     * 上一个节点id
     */
    @TableField
    private Long prevNodeId;

    /**
     * 对应结果类型
     */
    @TableField
    private Long resultId;

    /**
     * 下一个节点id
     */
    @TableField
    private Long nextNodeId;


    public Long getPrevNodeId() {
        return prevNodeId;
    }

    public void setPrevNodeId(Long prevNodeId) {
        this.prevNodeId = prevNodeId;
    }


    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }


    public Long getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(Long nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

}
