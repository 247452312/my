package indi.uhyils.aop;

import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.util.AopUtil;
import indi.uhyils.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 异常AOP
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月23日 07时12分
 */
@Component
@Aspect
@Order(30)
public class ExceptionAop {


    /**
     * 定义切入点，切入点为indi.uhyils.serviceImpl包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * indi.uhyils.serviceImpl.*.*(..)))")
    public void exceptionAspectPoint() {
    }

    @Around("exceptionAspectPoint()")
    public Object exceptionAroundAspect(ProceedingJoinPoint pjp) throws Exception {
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            LogUtil.error(this, e);
            return ServiceResult.buildErrorResult(e.getMessage(), AopUtil.getDefaultRequestInPjp(pjp));
        }
    }
}
