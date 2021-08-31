package indi.uhyils.aop;

import indi.uhyils.exception.AssertException;
import indi.uhyils.pojo.DTO.base.ServiceResult;
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
    @Pointcut("execution(public indi.uhyils.pojo.DTO.base.ServiceResult indi.uhyils.protocol..*.*(..))))")
    public void exceptionAspectPoint() {
    }

    @Around("exceptionAspectPoint()")
    public Object exceptionAroundAspect(ProceedingJoinPoint pjp) throws Exception {
        try {
            return pjp.proceed();
        } catch (AssertException e) {
            LogUtil.error(e.getMessage());
            return ServiceResult.buildFailedResult(e.getMessage());
        } catch (Throwable e) {
            LogUtil.error(this, e);
            return ServiceResult.buildErrorResult(e.getMessage());
        }
    }
}
