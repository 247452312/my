package indi.uhyils.core.register;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 注册者管理者
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 22时02分
 * @Version 1.0
 */
public class RegisterKeeper {

    /**
     * 注册的所有者
     */
    private static final Map<RegisterType, Collection<Register>> REGISTER_TYPE_COLLECTION_MAP = new HashMap<>();

    static {
        // 初始化
        for (RegisterType value : RegisterType.values()) {
            REGISTER_TYPE_COLLECTION_MAP.put(value, new ArrayList<>());
        }
    }

    public static Collection<Register> findRegister(RegisterType type, String ip, Integer port, String topicName) {
        Collection<Register> basic = new ArrayList<>();
        // 过滤类型
        if (type == null) {
            REGISTER_TYPE_COLLECTION_MAP.values().forEach(basic::addAll);
        } else {
            basic.addAll(REGISTER_TYPE_COLLECTION_MAP.get(type));
        }
        Stream<Register> basicStream = basic.stream();
        // 过滤ip
        if (ip != null) {
            basicStream = basicStream.filter(t -> ip.equals(t.getIp()));
        }
        // 过滤端口
        if (port != null) {
            basicStream = basicStream.filter(t -> port.equals(t.getPort()));
        }
        // 过滤
        if (topicName != null) {
            basicStream = basicStream.filter(t -> topicName.equals(t.getTopicName()));
        }
        return basicStream.collect(Collectors.toSet());
    }


}
