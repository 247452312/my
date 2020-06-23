package indi.uhyils.pojo.request.base;

import indi.uhyils.pojo.request.model.LinkNode;

import java.io.Serializable;

/**
 * 查询用
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时27分
 */
public class ObjRequest<T extends Serializable> extends DefaultRequest {

    /**
     * 微服务传入的data
     */
    private T data;

    public static <T extends Serializable> ObjRequest<T> build(T data, String token) {
        ObjRequest<T> serializableObjRequest = new ObjRequest<>();
        serializableObjRequest.setData(data);
        serializableObjRequest.setToken(token);
        serializableObjRequest.setRequestLink(new LinkNode<>());
        return serializableObjRequest;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
