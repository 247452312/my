package indi.uhyils.pojo.entity;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import java.util.List;
import java.util.Map;

/**
 * provider实例
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月07日 19时56分
 */
public interface ProviderExample {

    /**
     * 执行数据提供者
     *
     * @param header       header头
     * @param params       入参
     * @param shouldParams 入参对应的信息
     */
    NodeInvokeResult invoke(Map<String, String> header, Map<String, Object> params, List<ProviderInterfaceParam> shouldParams);

    /**
     * 填充父类
     *
     * @param providerInterface
     */
    void fillInterface(ProviderInterface providerInterface);
}
