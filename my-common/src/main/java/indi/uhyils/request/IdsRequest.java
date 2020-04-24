package indi.uhyils.request;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时10分
 */
public class IdsRequest extends DefaultRequest {

    /**
     * id们
     */
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
