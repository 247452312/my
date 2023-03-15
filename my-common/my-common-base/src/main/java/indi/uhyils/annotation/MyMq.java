package indi.uhyils.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 15时45分
 */
@Documented
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyMq {

    /**
     * 监听的topic
     *
     * @return
     */
    String topic();

    /**
     * 监听的tags
     *
     * @return
     */
    String[] tags();

    /**
     * consumer本身的组名称
     *
     * @return
     */
    String group();
}
