package indi.uhyils.pojo.cqe;

import indi.uhyils.pojo.cqe.query.base.AbstractQuery;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询rpc swagger 的请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 14时22分
 */
public class FindSwaggerQuery extends AbstractQuery {

    @ApiModelProperty("查询文字")
    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
