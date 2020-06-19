package indi.uhyils.pojo.request;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 08时23分
 */
public class ObjsRequest<T extends Serializable> extends DefaultRequest {

    private List<T> list;

    public static <T extends Serializable> ObjsRequest<T> build(List<T> list) {
        ObjsRequest<T> objsRequest = new ObjsRequest<>();
        objsRequest.setList(list);
        return objsRequest;
    }

    @SafeVarargs
    public static <T extends Serializable> ObjsRequest<T> build(T... list) {
        return build(Arrays.asList(list));
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
