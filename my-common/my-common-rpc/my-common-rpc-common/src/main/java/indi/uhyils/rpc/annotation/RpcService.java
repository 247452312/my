package indi.uhyils.rpc.annotation;

import java.lang.annotation.*;

/**
 * 使用这个类可以将此类做成一个rpc bean 等待调用
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月29日 07时19分
 */
@Documented
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcService {


}
