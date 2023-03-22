package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.MySwagger;
import indi.uhyils.pojo.DTO.ClassSwaggerDTO;
import indi.uhyils.pojo.DTO.HttpClassSwaggerDTO;
import indi.uhyils.util.SwaggerUtils;

/**
 * controller swagger解释,解析的swagger包括类上的注解,所有公共方法,以及方法的入参出参,注释等继承swagger包的注解
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月22日 10时59分
 */
public class ControllerClassSwaggerEntity extends ClassSwaggerEntity {


    public ControllerClassSwaggerEntity(Class<?> targetClass) {
        super(targetClass);
    }

    @Override
    protected ClassSwaggerDTO parseClass() {
        HttpClassSwaggerDTO httpClassSwaggerDTO = new HttpClassSwaggerDTO();
        httpClassSwaggerDTO.setTypeCode(annotation.value().getCode());
        httpClassSwaggerDTO.setTypeName(annotation.value().toString());
        httpClassSwaggerDTO.setName(targetClass.getName());
        httpClassSwaggerDTO.setDesc(annotation.desc());
        httpClassSwaggerDTO.setMethods(SwaggerUtils.parseToRpcMethods(targetClass));
        return httpClassSwaggerDTO;
    }
}
