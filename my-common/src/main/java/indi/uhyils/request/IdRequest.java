package indi.uhyils.request;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时06分
 */
public class IdRequest extends DefaultRequest {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public static IdRequest build(String id) {
        IdRequest idRequest = new IdRequest();
        idRequest.setId(id);
        return idRequest;
    }
}
