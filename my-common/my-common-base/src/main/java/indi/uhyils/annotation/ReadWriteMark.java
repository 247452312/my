package indi.uhyils.annotation;

import indi.uhyils.enum_.ReadWriteTypeEnum;

import java.lang.annotation.*;

/**
 * 读写标记接口 如果方法上有此注解,优先应用接口上的注解,如果方法和接口上均无此注解,则认为此接口默认为读接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 07时58分
 */
@Documented
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadWriteMark {
    /**
     * 标记此接口或方法是读还是写
     *
     * @return
     */
    ReadWriteTypeEnum type() default ReadWriteTypeEnum.READ;
}
