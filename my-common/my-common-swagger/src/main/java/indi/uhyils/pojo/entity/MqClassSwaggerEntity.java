package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.MyMq;
import indi.uhyils.annotation.MySwagger;
import indi.uhyils.enums.ProtocolTypeEnum;
import indi.uhyils.pojo.DTO.ClassSwaggerDTO;
import indi.uhyils.pojo.DTO.MqClassSwaggerDTO;
import java.util.Arrays;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月22日 10时58分
 */
public class MqClassSwaggerEntity extends ClassSwaggerEntity {


    public MqClassSwaggerEntity(Class<?> targetClass) {
        super(targetClass);
    }

    @Override
    protected ClassSwaggerDTO parseClass() {
        MqClassSwaggerDTO swaggerDTO = new MqClassSwaggerDTO();

        MyMq myMq = targetClass.getAnnotation(MyMq.class);
        ProtocolTypeEnum value = annotation.value();
        swaggerDTO.setTypeCode(value.getCode());
        swaggerDTO.setTypeName(value.toString());
        swaggerDTO.setName(targetClass.getName());
        swaggerDTO.setDesc(annotation.desc());
        swaggerDTO.setTopic(myMq.topic());
        swaggerDTO.setTag(Arrays.asList(myMq.tags()));
        swaggerDTO.setConsumerInfo(myMq.group());
        return swaggerDTO;
    }
}
