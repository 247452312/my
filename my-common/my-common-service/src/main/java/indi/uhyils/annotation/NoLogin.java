package indi.uhyils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户登录验证时 不进行用户可登录验证
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月15日 11时43分
 */
@Inherited  //可以被继承
@Retention(RetentionPolicy.RUNTIME) // 反射可以读取
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NoLogin {

}
