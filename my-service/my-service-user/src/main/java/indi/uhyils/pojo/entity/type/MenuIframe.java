package indi.uhyils.pojo.entity.type;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 15时55分
 */
public class MenuIframe implements BaseType {

    private Integer iframe;

    public MenuIframe(Integer iframe) {
        this.iframe = iframe;
    }

    public Integer getIframe() {
        return iframe;
    }

    public void setIframe(Integer iframe) {
        this.iframe = iframe;
    }
}
