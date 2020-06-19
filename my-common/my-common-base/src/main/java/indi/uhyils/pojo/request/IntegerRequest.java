package indi.uhyils.pojo.request;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时06分
 */
public class IntegerRequest extends DefaultRequest {

    private Integer data;

    public static IntegerRequest build(Integer data) {
        IntegerRequest integerRequest = new IntegerRequest();
        integerRequest.setData(data);
        return integerRequest;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
