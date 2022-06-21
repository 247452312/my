package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.base.BaseQuery;

/**
 * 根据groupId获取动态代码
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月14日 19时18分
 */
public class GetByGroupIdQuery extends DefaultCQE implements BaseQuery {

    /**
     * 代码组id
     */
    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
