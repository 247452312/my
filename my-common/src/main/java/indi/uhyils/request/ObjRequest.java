package indi.uhyils.request;

import java.io.Serializable;

/**
 * 查询用
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时27分
 */
public class ObjRequest<T extends Serializable> extends DefaultRequest  {

    /**
     * 微服务传入的data
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
