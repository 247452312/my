package indi.uhyils.pojo.entity.type;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月06日 21时57分
 */
public class Iframe implements BaseType {

    private Integer iframe;

    public Iframe(Integer iframe) {
        this.iframe = iframe;
    }

    public Integer getIframe() {
        return iframe;
    }

    public void setIframe(Integer iframe) {
        this.iframe = iframe;
    }
}
