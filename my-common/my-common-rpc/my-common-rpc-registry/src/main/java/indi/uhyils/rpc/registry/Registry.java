package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.exception.RpcException;

/**
 * 这里的T指service
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时30分
 */
public interface Registry<T> {

    /**
     * 远程调用
     *
     * @param unique
     * @param methodName
     * @param paramType
     * @param args
     * @return
     */
    String invoke(Long unique, String methodName, Class[] paramType, Object[] args) throws RpcException, ClassNotFoundException, InterruptedException;


}
