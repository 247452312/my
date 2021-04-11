package indi.uhyils.core.exception;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时38分
 * @Version 1.0
 */
public class TopicTypeNotFoundException extends UserException {

    public TopicTypeNotFoundException() {
        super("您传送的topic类型不存在");
    }
}
