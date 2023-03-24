package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.MySwagger;
import indi.uhyils.pojo.DTO.ClassSwaggerDTO;
import indi.uhyils.pojo.DTO.TaskClassSwaggerDTO;
import indi.uhyils.protocol.task.BaseTask;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SwaggerUtils;
import java.lang.reflect.Method;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月22日 10时58分
 */
public class TaskClassSwaggerEntity extends ClassSwaggerEntity {

    public TaskClassSwaggerEntity(Class<?> targetClass) {
        super(targetClass);
    }

    @Override
    protected ClassSwaggerDTO parseClass() {
        MySwagger annotation = targetClass.getAnnotation(MySwagger.class);
        TaskClassSwaggerDTO taskClassSwaggerDTO = new TaskClassSwaggerDTO();
        taskClassSwaggerDTO.setServiceType(annotation.value());
        taskClassSwaggerDTO.setName(targetClass.getName());
        taskClassSwaggerDTO.setDesc(annotation.desc());
        Method method = BaseTask.class.getMethods()[0];
        try {
            Method declaredMethod = targetClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
            taskClassSwaggerDTO.setMethod(SwaggerUtils.parseToRpcMethod(targetClass, declaredMethod));
        } catch (NoSuchMethodException e) {
            LogUtil.error(this, e);
        }
        return taskClassSwaggerDTO;
    }
}
