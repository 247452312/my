package indi.uhyils.pojo.request.base;

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
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
