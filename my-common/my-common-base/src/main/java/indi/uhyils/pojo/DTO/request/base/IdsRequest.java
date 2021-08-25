package indi.uhyils.pojo.DTO.request.base;

import java.util.List;

/**
 * 多个id的请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时10分
 */
public class IdsRequest extends DefaultRequest {

    /**
     * id们
     */
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
