package indi.uhyils.rpc.annotation;

import java.lang.annotation.*;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月29日 07时19分
 */
@Documented
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcReference {
}
