package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.MyMq;
import indi.uhyils.annotation.MySwagger;
import indi.uhyils.enums.ProtocolTypeEnum;
import indi.uhyils.pojo.DTO.ClassSwaggerDTO;
import indi.uhyils.pojo.DTO.HttpClassSwaggerDTO;
import indi.uhyils.pojo.DTO.MqClassSwaggerDTO;
import indi.uhyils.pojo.DTO.RpcClassSwaggerDTO;
import indi.uhyils.pojo.DTO.TaskClassSwaggerDTO;
import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.protocol.task.BaseTask;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.ClassUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SwaggerUtils;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 14时39分
 */
public class ClassSwaggerEntity extends AbstractEntity<String> {

    /**
     * 当前swagger承载的信息
     */
    private ClassSwaggerDTO swaggerDTO;

    public ClassSwaggerEntity(Object targetObj) throws Exception {
        this(ClassUtil.getRealClass(targetObj));
    }

    public <T> ClassSwaggerEntity(Class<T> targetClass) {
        MySwagger annotation = targetClass.getAnnotation(MySwagger.class);
        ProtocolTypeEnum value = annotation.value();
        switch (value) {
            case MQ:
                initMQClass(targetClass);
                break;
            case RPC:
                initRpcClass(targetClass);
                break;
            case HTTP:
                initControllerClass(targetClass);
                break;
            case TASK:
                initTaskClass(targetClass);
                break;
            default:
                Asserts.throwException("未知的协议类型:{}", value);
        }
    }

    /**
     * 初始化MQ对应的class的swagger
     *
     * @param targetClass
     * @param <T>
     */
    private <T> void initMQClass(Class<T> targetClass) {
        MqClassSwaggerDTO swaggerDTO = new MqClassSwaggerDTO();

        MySwagger annotation = targetClass.getAnnotation(MySwagger.class);
        MyMq myMq = targetClass.getAnnotation(MyMq.class);
        ProtocolTypeEnum value = annotation.value();
        swaggerDTO.setTypeCode(value.getCode());
        swaggerDTO.setTypeName(value.toString());
        swaggerDTO.setName(targetClass.getName());
        swaggerDTO.setDesc(annotation.desc());
        swaggerDTO.setTopic(myMq.topic());
        swaggerDTO.setTag(Arrays.asList(myMq.tags()));
        swaggerDTO.setConsumerInfo(myMq.group());
        this.swaggerDTO = swaggerDTO;
    }

    /**
     * 初始化rpc对应的class的swagger
     *
     * @param targetClass
     * @param <T>
     */
    private <T> void initRpcClass(Class<T> targetClass) {
        MySwagger annotation = targetClass.getAnnotation(MySwagger.class);
        RpcClassSwaggerDTO rpcClassSwaggerDTO = new RpcClassSwaggerDTO();
        rpcClassSwaggerDTO.setTypeCode(annotation.value().getCode());
        rpcClassSwaggerDTO.setTypeName(annotation.value().toString());
        rpcClassSwaggerDTO.setName(targetClass.getName());
        rpcClassSwaggerDTO.setDesc(annotation.desc());
        rpcClassSwaggerDTO.setMethods(SwaggerUtils.parseToRpcMethods(targetClass));
        this.swaggerDTO = rpcClassSwaggerDTO;
    }

    /**
     * 初始化http对应的class的swagger
     *
     * @param targetClass
     * @param <T>
     */
    private <T> void initControllerClass(Class<T> targetClass) {
        MySwagger annotation = targetClass.getAnnotation(MySwagger.class);

        HttpClassSwaggerDTO httpClassSwaggerDTO = new HttpClassSwaggerDTO();
        httpClassSwaggerDTO.setTypeCode(annotation.value().getCode());
        httpClassSwaggerDTO.setTypeName(annotation.value().toString());
        httpClassSwaggerDTO.setName(targetClass.getName());
        httpClassSwaggerDTO.setDesc(annotation.desc());
        httpClassSwaggerDTO.setMethods(SwaggerUtils.parseToRpcMethods(targetClass));
        this.swaggerDTO = httpClassSwaggerDTO;
    }

    /**
     * 初始化task对应的class的swagger
     *
     * @param targetClass
     * @param <T>
     */
    private <T> void initTaskClass(Class<T> targetClass) {
        MySwagger annotation = targetClass.getAnnotation(MySwagger.class);
        TaskClassSwaggerDTO taskClassSwaggerDTO = new TaskClassSwaggerDTO();
        taskClassSwaggerDTO.setTypeCode(annotation.value().getCode());
        taskClassSwaggerDTO.setTypeName(annotation.value().toString());
        taskClassSwaggerDTO.setName(targetClass.getName());
        taskClassSwaggerDTO.setDesc(annotation.desc());
        Method method = BaseTask.class.getMethods()[0];
        try {
            Method declaredMethod = targetClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
            taskClassSwaggerDTO.setMethod(SwaggerUtils.parseToRpcMethod(declaredMethod));
        } catch (NoSuchMethodException e) {
            LogUtil.error(this, e);
        }
        swaggerDTO = taskClassSwaggerDTO;
    }

    public ClassSwaggerDTO toDTO() {
        return swaggerDTO;
    }
}
