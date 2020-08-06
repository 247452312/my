package indi.uhyils.util.redis;

import indi.uhyils.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * redis加锁aop
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月06日 07时51分
 */
@Lazy
@Component
@Aspect
public class OffLineRedisAop {

    /**
     * 定义切入点，切入点为offlineRedis类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * indi.uhyils.util.redis.OffLineJedis.*(*)))")
    public void offLineRedisAop() {
    }

    @Around("offLineRedisAop()")
    public Object offLineRedisAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        OffLineJedis.lock.lock();
        try {
            return pjp.proceed();
        } catch (Exception e) {
            LogUtil.error(this, e);
        } finally {
            OffLineJedis.lock.unlock();
        }
        return null;
    }
}
