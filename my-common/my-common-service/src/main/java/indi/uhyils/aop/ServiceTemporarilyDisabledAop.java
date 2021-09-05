package indi.uhyils.aop;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.redis.RedisPoolHandle;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 方法临时禁用aop
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 08时24分
 */
@Component
@Aspect
@Order(10)
public class ServiceTemporarilyDisabledAop {


    @Autowired
    private RedisPoolHandle redisPoolHandle;

    /**
     * 定义切入点，切入点为indi.uhyils.serviceImpl包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public indi.uhyils.pojo.DTO.base.ServiceResult indi.uhyils.protocol..*.*(..)))")
    public void serviceTemporarilyDisabledAspectPoint() {
    }

    @Around("serviceTemporarilyDisabledAspectPoint()")
    public Object serviceTemporarilyDisabledAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> targetClass = pjp.getTarget().getClass();
        String name = pjp.getSignature().getName();
        List<Class> classList = new ArrayList<>();
        classList.add(targetClass);
        Class<?>[] interfaces = targetClass.getInterfaces();
        classList.addAll(Arrays.asList(interfaces));
        if (interfaces != null && interfaces.length != 0) {
            interfaces = interfaces[0].getInterfaces();
            classList.addAll(Arrays.asList(interfaces));
        }
        List<Method> methodList = new ArrayList<>();
        for (Class aClass : classList) {
            Method[] declaredMethods = aClass.getDeclaredMethods();
            methodList.addAll(Arrays.asList(declaredMethods));
        }
        Method declaredMethod = null;
        for (Method method : methodList) {
            String methodName = method.getName();
            if (methodName.equals(name)) {
                declaredMethod = method;
            }
        }
        if (declaredMethod == null) {
            return pjp.proceed();
        }
        ReadWriteTypeEnum methodType = null;
        ReadWriteMark readWriteMark = null;

        // 先找类上的注解,如果有,则设置
        ReadWriteMark declaredAnnotation = targetClass.getDeclaredAnnotation(ReadWriteMark.class);
        if (declaredAnnotation != null) {
            methodType = declaredAnnotation.type();
            readWriteMark = declaredAnnotation;
        }

        // 找方法上的对应注解,如果有,则覆盖类上的方法
        declaredMethod.setAccessible(Boolean.FALSE);
        ReadWriteMark methodDeclaredAnnotation = declaredMethod.getDeclaredAnnotation(ReadWriteMark.class);
        if (methodDeclaredAnnotation != null) {
            methodType = methodDeclaredAnnotation.type();
            readWriteMark = methodDeclaredAnnotation;
        }

        // 如果方法和类上都没有,则默认为读接口
        if (methodType == null) {
            methodType = ReadWriteTypeEnum.READ;
        }

        Boolean allowRun = redisPoolHandle.checkMethodDisable(targetClass, declaredMethod, methodType == ReadWriteTypeEnum.READ ? 1 : 2);
        if (allowRun) {
            return pjp.proceed();
        } else {
            return ServiceResult.buildFailedResult("请求接口已被禁用", null);
        }


    }
}
