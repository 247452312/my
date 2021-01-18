package indi.uhyils.rpc.pojo;

import org.apache.commons.lang3.StringUtils;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时00分
 */
public class RpcHeaderFactory {

    public static RpcHeader newInstance(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        RpcHeader rpcHeader = new RpcHeader();
        int colonIndex = data.indexOf(':');
        String name = data.substring(0, colonIndex);
        String value = data.substring(colonIndex + 1);
        rpcHeader.setName(name);
        rpcHeader.setValue(value);
        return rpcHeader;
    }
}
