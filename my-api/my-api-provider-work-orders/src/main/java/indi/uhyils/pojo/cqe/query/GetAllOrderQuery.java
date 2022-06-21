package indi.uhyils.pojo.cqe.query;


import indi.uhyils.pojo.cqe.query.base.AbstractArgQuery;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月04日 15时54分
 */
public class GetAllOrderQuery extends AbstractArgQuery {

    /**
     * 要获取的工单类型
     */
    private Integer type;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
