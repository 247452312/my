package indi.uhyils.rpc.registry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月08日 08时42分
 */
public class RpcContext {

    /**
     * 服务端注册中心
     */
    public static List<ProviderRegistry> providerRegistry = new LinkedList<>();

    public static List<ConsumerRegistry> consumerRegistry = new ArrayList<>();

}
