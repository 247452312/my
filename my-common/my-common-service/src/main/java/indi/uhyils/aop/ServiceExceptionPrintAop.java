package indi.uhyils.aop;

import indi.uhyils.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月05日 13时16分
 */
@Component
@Aspect
public class ServiceExceptionPrintAop {

    /**
     * 定义切入点，切入点为indi.uhyils.serviceImpl包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * indi.uhyils.service.impl.*.*(..))))")
    public void serviceExceptionPrintPoint() {
    }

    @Around("serviceExceptionPrintPoint()")
    public Object exceptionAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            LogUtil.error(this, e);
            throw e;
        }
    }

}
