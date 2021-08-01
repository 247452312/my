package indi.uhyils.pojo.request.base;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月01日 13时34分
 */
public class LongRequest extends DefaultRequest {

    /**
     * long值
     */
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
