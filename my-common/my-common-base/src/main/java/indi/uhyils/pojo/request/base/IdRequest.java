package indi.uhyils.pojo.request.base;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时06分
 */
public class IdRequest extends DefaultRequest {

    private Long id;

    public IdRequest() {
    }

    public IdRequest(DefaultRequest request) {
        super(request);
    }

    public static IdRequest build(Long id) {
        IdRequest idRequest = new IdRequest();
        idRequest.setId(id);
        return idRequest;
    }

    public static IdRequest build(DefaultRequest request, Long id) {
        IdRequest idRequest = new IdRequest(request);
        idRequest.setId(id);
        return idRequest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
