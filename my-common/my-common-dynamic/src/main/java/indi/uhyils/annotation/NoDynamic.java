package indi.uhyils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释在方法上,可以使方法不接受,不执行动态执行的代码
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月14日 20时31分
 */
@Inherited  //可以被继承
@Retention(RetentionPolicy.RUNTIME) // 反射可以读取
@Target({ElementType.METHOD})
public @interface NoDynamic {

}
