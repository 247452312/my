package indi.uhyils.aop;

import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.util.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * 自定义日志
     */
    private Logger logger = LoggerFactory.getLogger(ExceptionAop.class);


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
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ServiceResult.buildErrorResult(e.getMessage(), AopUtil.getDefaultRequestInPjp(pjp));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error(throwable.getMessage());
            return ServiceResult.buildErrorResult(throwable.getMessage(), AopUtil.getDefaultRequestInPjp(pjp));
        }
    }
}
