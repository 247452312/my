package indi.uhyils.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月13日 08时57分
 */
@Documented
@Target(value = {ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

}
