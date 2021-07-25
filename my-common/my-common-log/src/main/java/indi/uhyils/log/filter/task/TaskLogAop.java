package indi.uhyils.log.filter.task;

import indi.uhyils.log.MyTraceIdContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月25日 22时08分
 */
@Component
@Aspect
public class TaskLogAop {


    /**
     * 定义切入点，切入点为所有spring定时任务函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void taskTraceInjectPoint() {
    }

    @Around("taskTraceInjectPoint()")
    public Object exceptionAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        MyTraceIdContext.init();
        Object proceed;
        try {
            proceed = pjp.proceed();
        } finally {
            MyTraceIdContext.clean();
        }
        return proceed;
    }

}
