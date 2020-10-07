package indi.uhyils.annotation;

import indi.uhyils.enum_.ReadWriteTypeEnum;

import java.lang.annotation.*;

/**
 * 读写标记接口 如果方法上有此注解,优先应用接口上的注解,如果方法和接口上均无此注解,则认为此接口默认为读接口
 * 通用接口{@link indi.uhyils.service.base.DefaultEntityService}中的方法继承实现类上的此注解,若均没有,那么默认此接口不读取数据库
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 07时58分
 */
@Documented
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadWriteMark {
    /**
     * 标记此接口或方法是读还是写,默认是写接口,在接口禁用功能中,如果禁用的功能是类的所有读接口,就是用此注解来识别
     * 如果禁用的是方法,则优先级要高于类上的禁用
     * <p>
     * e.g.
     * 1. 接口全部禁用,方法禁用类型为无--> 可用
     * 2. 接口全部禁用,方法禁用类型为禁用-->禁用
     * 3. 接口不禁用,方法禁用类型为禁用 --> 禁用
     * 4. 接口禁用所有方法,方法无禁用条件 --> 禁用
     */
    ReadWriteTypeEnum type() default ReadWriteTypeEnum.READ;

    /**
     * 此接口影响的tables
     */
    String[] tables() default {};


    /**
     * 缓存类型
     * 1->可以进入缓存,同角色都可以查询
     * 2->可以进入缓存,同部门都可以查询
     * 3->不能进入缓存
     */
    int cacheType() default 3;


}
