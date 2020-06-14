package indi.uhyils.pojo.request;

/**
 * 获取服务器名称
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 21时29分
 */
public class GetNameByIdRequest extends DefaultRequest {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
