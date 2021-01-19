package indi.uhyils.rpc.exchange.pojo;

/**
 * rpc内容抽象类,里面不包含rpc的version等信息, 只有正式内容,例如请求时的请求接口名称,接口版本等信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 10时25分
 */
public interface RpcContent {
    /**
     * 获取类型
     *
     * @return
     */
    Integer type();

    /**
     * 重写toString方法
     *
     * @return
     */
    @Override
    String toString();


    /**
     * 获取第x行
     *
     * @param line
     * @return
     */
    String getLine(Integer line);

    /**
     * 观察者模式,获取父级
     *
     * @return
     */
    RpcData getRpcData();
}
