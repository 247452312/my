package indi.uhyils.aop;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.redis.RedisPoolUtil;
import indi.uhyils.util.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
    private RedisPoolUtil redisPoolUtil;

    /**
     * 定义切入点，切入点为indi.uhyils.serviceImpl包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public indi.uhyils.pojo.response.base.ServiceResult indi.uhyils.serviceImpl.*.*(indi.uhyils.pojo.request.base.DefaultRequest)))")
    public void serviceTemporarilyDisabledAspectPoint() {
    }

    @Around("serviceTemporarilyDisabledAspectPoint()")
    public Object exceptionAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> targetClass = pjp.getTarget().getClass();
        String name = pjp.getSignature().getName();
        Method[] declaredMethods = targetClass.getDeclaredMethods();
        Method declaredMethod = targetClass.getDeclaredMethod(name, DefaultRequest.class);
        ReadWriteTypeEnum methodType = null;

        // 先找类上的注解,如果有,则设置
        ReadWriteMark declaredAnnotation = targetClass.getDeclaredAnnotation(ReadWriteMark.class);
        if (declaredAnnotation != null) {
            methodType = declaredAnnotation.type();
        }

        // 找方法上的对应注解,如果有,则覆盖类上的方法
        declaredMethod.setAccessible(false);
        ReadWriteMark methodDeclaredAnnotation = declaredMethod.getDeclaredAnnotation(ReadWriteMark.class);
        if (methodDeclaredAnnotation != null) {
            methodType = methodDeclaredAnnotation.type();
        }

        // 如果方法和类上都没有,则默认为读接口
        if (methodType == null) {
            methodType = ReadWriteTypeEnum.READ;
        }
        Boolean allowRun = redisPoolUtil.checkMethodDisable(targetClass, declaredMethod, methodType == ReadWriteTypeEnum.READ ? 0 : 1);
        if (allowRun) {
            return pjp.proceed();
        } else {
            DefaultRequest arg = AopUtil.getDefaultRequestInPjp(pjp);
            return ServiceResult.buildFailedResult("请求接口已被禁用", null, arg);
        }


    }
}
