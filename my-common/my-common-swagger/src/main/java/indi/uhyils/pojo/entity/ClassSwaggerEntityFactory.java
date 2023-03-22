package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.MySwagger;
import indi.uhyils.util.Asserts;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月22日 13时54分
 */
public class ClassSwaggerEntityFactory {

    /**
     * 根据class获取真实的swaggerEntity
     *
     * @param realClass
     *
     * @return
     */
    public static ClassSwaggerEntity createByClass(Class<?> realClass) {
        MySwagger annotation = realClass.getAnnotation(MySwagger.class);
        switch (annotation.value()) {
            case TASK:
                return new TaskClassSwaggerEntity(realClass);
            case HTTP:
                return new ControllerClassSwaggerEntity(realClass);
            case RPC:
                return new RpcClassSwaggerEntity(realClass);
            case MQ:
                return new MqClassSwaggerEntity(realClass);
            default:
                Asserts.throwException("未知的协议对应swagger类型:{}", annotation.value());
                return null;
        }
    }

}
