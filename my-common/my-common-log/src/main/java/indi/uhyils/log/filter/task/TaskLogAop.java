package indi.uhyils.log.filter.task;

import indi.uhyils.context.MyTraceIdContext;
import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.util.LogUtil;
import java.text.MessageFormat;
import java.util.function.Supplier;
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
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        Supplier<Object> objectSupplier = () -> {
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                LogUtil.error(throwable);
            } finally {
                LogUtil.info(MessageFormat.format("{0}#{1}定时任务结束!", className, methodName));
            }
            return null;
        };
        try {
            return MyTraceIdContext.printLogInfo(LogTypeEnum.TASK, objectSupplier::get, new String[]{className, methodName}, className, methodName);
        } finally {
            MyTraceIdContext.clean();
        }
    }

}
