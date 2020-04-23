package indi.uhyils.function.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 07时45分
 */
@Component
@Aspect
public class TimeLogAop {

    /**
     * 自定义日志
     */
    private Logger logger = LoggerFactory.getLogger(TimeLogAop.class);

    public StringBuilder sb = new StringBuilder();

    /**
     * 定义切入点，切入点为indi.uhyils.serviceImpl包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * indi.uhyils.serviceImpl.*.*(..)))")
    public void logAspectPoint() {
    }


    @Around("logAspectPoint()")
    public Object timeLogAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getCanonicalName();
        String methodName = pjp.getSignature().getName();
        //方法执行前显示 类名,方法名,参数名
        before(pjp, className, methodName);
        Long startTime = System.currentTimeMillis();
        //执行方法
        Object proceed = pjp.proceed();
        Long endTime = System.currentTimeMillis();
        double v = (endTime - startTime) / 1000.0;

        after(className, methodName, v);
        return proceed;


    }

    /**
     * 日志切面后
     *
     * @param className  类名称
     * @param methodName 方法名称
     * @param v          执行时间
     */
    private void after(String className, String methodName, double v) {
        logger.info(String.format("%s类中的%s方法执行完毕,执行时间为%f秒", className, methodName, v));
    }

    /**
     * 日志切面前
     *
     * @param pjp        切点类,没啥说的
     * @param className  类名
     * @param methodName 方法名
     */
    private void before(ProceedingJoinPoint pjp, String className, String methodName) {
        Object[] args = pjp.getArgs();
        sb.append(className);
        sb.append("类中的");
        sb.append(methodName);
        sb.append("方法开始执行,参数为:");
        for (Object arg : args) {
            sb.append(arg.toString());
            sb.append("(");
            sb.append(arg.getClass().getSimpleName());
            sb.append(")");
        }
        logger.info(sb.toString());
        sb.delete(0, sb.length());
    }
}
