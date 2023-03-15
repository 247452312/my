package indi.uhyils.annotation;

import indi.uhyils.enums.ProtocolTypeEnum;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 协议层 对应的swagger
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 14时17分
 */
@Documented
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MySwagger {

    /**
     * 协议类型
     */
    ProtocolTypeEnum value();


    /**
     * class说明
     *
     * @return
     */
    String desc() default "";


}
