package indi.uhyils.core.exception;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 20时26分
 * @Version 1.0
 */
public class PartitionTopicNoKeyException extends UserException {

    public PartitionTopicNoKeyException() {
        super("创建分区顺序消息时,不能创建没有key的topic");
    }
}
