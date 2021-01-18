package indi.uhyils.rpc.annotation;

import java.lang.annotation.*;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月18日 07时58分
 */
@Documented
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcSpi {

    /**
     * 排序,数值越小越靠前
     *
     * @return
     */
    int order() default 50;
}
