package indi.uhyils.util.disruptor;

/**
 * Disruptor 日志事件
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月09日 07时01分
 */
public class JsonEvent {

    private String value;

    private String token;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
