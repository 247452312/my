package indi.uhyils.rpc.registry;

/**
 * 服务提供者注册层
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月04日 08时56分
 */
public interface ProviderRegistry extends Registry {

    /**
     * 开始对外提供服务
     */
    void allowToPublish();

    /**
     * 不对外提供服务
     */
    void notAllowToPublish();


    /**
     * 查询对外提供服务的状态
     *
     * @return
     */
    Boolean publishStatus();

}
