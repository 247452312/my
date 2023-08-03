package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.MySwagger;
import indi.uhyils.enums.ProtocolTypeEnum;
import indi.uhyils.pojo.DTO.ClassSwaggerDTO;
import indi.uhyils.pojo.entity.base.AbstractEntity;

/**
 * 类的swagger说明 ,类型有多种{@link ProtocolTypeEnum}
 * 具体如何解析留给子类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 14时39分
 */
public abstract class ClassSwaggerEntity extends AbstractEntity<String> {

    /**
     * class
     */
    protected Class<?> targetClass;

    /**
     * 类带着的MySwagger注解
     */
    protected MySwagger annotation;

    /**
     * 当前swagger承载的信息
     */
    private ClassSwaggerDTO swaggerDTO;


    public ClassSwaggerEntity(Class<?> targetClass) {
        this.targetClass = targetClass;
        this.annotation = targetClass.getAnnotation(MySwagger.class);
        this.swaggerDTO = parseClass();
    }

    public ClassSwaggerDTO toDTO() {
        return swaggerDTO;
    }

    /**
     * 解析传入的class 子类应该去实现如何解析对应类型的类的swagger提示
     */
    protected abstract ClassSwaggerDTO parseClass();
}
