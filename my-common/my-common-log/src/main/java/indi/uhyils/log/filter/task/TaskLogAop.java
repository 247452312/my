package indi.uhyils.log.filter.task;

import com.google.common.base.Supplier;
import indi.uhyils.log.LogTypeEnum;
import indi.uhyils.log.MyTraceIdContext;
import indi.uhyils.util.LogUtil;
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
        MyTraceIdContext.init();
        Supplier<Object> objectSupplier = () -> {
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                LogUtil.error(throwable);
            } finally {
                LogUtil.info("定时任务结束!");
            }
            return null;
        };
        try {
            return MyTraceIdContext.printLogInfo(LogTypeEnum.TASK, objectSupplier, new String[]{className, methodName});
        } finally {
            MyTraceIdContext.clean();
        }
    }

}
