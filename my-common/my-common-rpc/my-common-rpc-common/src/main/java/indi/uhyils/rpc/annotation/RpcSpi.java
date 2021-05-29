package indi.uhyils.rpc.annotation;

import java.lang.annotation.*;

/**
 * 扩展注释,此注释和{META-INF/rpc/}下的文件配合使用
 *
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

    /**
     * 此扩展点的名称
     *
     * @return
     */
    String name() default "";
}
