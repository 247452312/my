package indi.uhyils.aop;

import indi.uhyils.annotation.NoDynamic;
import indi.uhyils.facade.DynamicCodeFacade;
import indi.uhyils.pojo.entity.RemoteDynamicCodeEntityInterface;
import indi.uhyils.pojo.entity.impl.RemoteDynamicCodeEntityImpl;
import indi.uhyils.rpc.content.HeaderContext;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Supplier;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月11日 19时20分
 */
@Component
@Aspect
@Order(30)
public class DynamicAop {

    @Autowired
    private DynamicCodeFacade facade;

    /**
     * 定义切入点，切入点为service包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public indi.uhyils.pojo.DTO.base.ServiceResult indi.uhyils.protocol..*.*(..)))")
    public void dynamicInjectPoint() {
    }

    @Around("dynamicInjectPoint()")
    public Object dynamicInjectAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        Map<String, String> header = HeaderContext.get();
        RemoteDynamicCodeEntityInterface dynamicAop = new RemoteDynamicCodeEntityImpl(header);
        // 替换本地classLoader
        dynamicAop.replaceClassLoaderFromContent();

        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        NoDynamic[] annotationsByType = targetMethod.getAnnotationsByType(NoDynamic.class);
        // 如果有noDynamic 那么就不会动态执行任何代码
        if (annotationsByType != null && annotationsByType.length != 0) {
            //执行方法
            return pjp.proceed();
        }
        // 如果没有匹配到,则直接执行
        if (!dynamicAop.isMatchSuccess()) {
            return pjp.proceed();
        }
        // 远程调用填充动态代码
        dynamicAop.fillDynamicCode(facade);
        Supplier<Object> supplier = () -> {
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        };
        if (dynamicAop.isTemp()) {
            return dynamicAop.tempDynamic(supplier);
        }
        return dynamicAop.permanentDynamic(supplier, true);

    }
}
