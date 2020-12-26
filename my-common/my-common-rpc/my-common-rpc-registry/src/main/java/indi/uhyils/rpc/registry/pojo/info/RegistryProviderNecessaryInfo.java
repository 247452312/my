package indi.uhyils.rpc.registry.pojo.info;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 17时32分
 */
public class RegistryProviderNecessaryInfo implements RegistryNecessaryInfo {

    /**
     * 要注册的服务的名称
     */
    private String serviceName;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 此服务的ip
     */
    private String host;

    /**
     * 此服务的端口
     */
    private String port;

    /**
     * rpc版本
     */
    private String rpcVersion;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRpcVersion() {
        return rpcVersion;
    }

    public void setRpcVersion(String rpcVersion) {
        this.rpcVersion = rpcVersion;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
