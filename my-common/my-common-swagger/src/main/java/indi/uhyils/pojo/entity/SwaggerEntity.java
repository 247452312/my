package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.MySwagger;
import indi.uhyils.content.SwaggerContent;
import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.util.ClassUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目级别的swagger
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 14时39分
 */
public class SwaggerEntity extends AbstractEntity<String> {

    private final List<Object> protocolObjs;

    public SwaggerEntity() {
        super(SpringUtil.getProperty(SwaggerContent.SWAGGER_APPLICATION_NAME_KEY));
        Map<String, Object> byAnnotation = SpringUtil.getByAnnotation(MySwagger.class);
        protocolObjs = new ArrayList<>(byAnnotation.values());
    }

    /**
     * 转化为类级别的swagger
     *
     * @return
     */
    public List<ClassSwaggerEntity> transClassSwagger() {
        List<ClassSwaggerEntity> list = new ArrayList<>();
        try {
            for (Object protocolObj : protocolObjs) {
                Class<?> realClass = ClassUtil.getRealClass(protocolObj);
                list.add(ClassSwaggerEntityFactory.createByClass(realClass));
            }
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
        return list;
    }
}