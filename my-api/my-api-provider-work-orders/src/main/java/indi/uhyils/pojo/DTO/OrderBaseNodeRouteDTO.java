package indi.uhyils.pojo.DTO;


/**
 * 节点间关联路由样例表(OrderBaseNodeRoute)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分09秒
 */
public class OrderBaseNodeRouteDTO extends IdDTO {

    private static final long serialVersionUID = 984209489712121861L;


    /**
     * 上一个节点id
     */
    private Long prevNodeId;

    /**
     * 对应结果类型
     */
    private Long resultId;

    /**
     * 下一个节点id
     */
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
