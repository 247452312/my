package indi.uhyils.annotation;

import java.lang.annotation.*;

/**
 * token验证时 不进行验证 直接执行方法
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月15日 11时43分
 */
@Inherited  //可以被继承
@Retention(RetentionPolicy.RUNTIME) // 反射可以读取
@Target({ElementType.METHOD})
public @interface NoToken {
}
