package indi.uhyils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
