package indi.uhyils.mq.aop;

import com.rabbitmq.client.Channel;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.mq.pojo.mqinfo.InterfaceCallInfo;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.mq.pojo.rabbit.RabbitFactory;
import indi.uhyils.mq.util.RabbitUtils;
import indi.uhyils.mq.util.ServiceUtil;
import indi.uhyils.pojo.response.base.ServiceResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月19日 06时59分
 */
@Component
@Aspect
@Order(41)
public class InterfaceCellAop {

    @Autowired
    private RabbitFactory rabbitFactory;

    @Autowired
    private JvmUniqueMark jvmUniqueMark;

    private volatile Channel channel;


    /**
     * 定义切入点，切入点为indi.uhyils.serviceImpl包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public indi.uhyils.pojo.response.base.ServiceResult indi.uhyils.serviceImpl.*.*(indi.uhyils.pojo.request.base.DefaultRequest)))")
    public void interfaceCellPoint() {
    }


    @Around("interfaceCellPoint()")
    public Object interfaceCellAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();

        Long startTime = System.currentTimeMillis();
        //执行方法
        Object proceed = pjp.proceed();
        ServiceResult sr = (ServiceResult) proceed;
        Long endTime = System.currentTimeMillis();

        long runTime = endTime - startTime;

        if (channel == null) {
            synchronized (InterfaceCellAop.class) {
                if (channel == null) {
                    channel = rabbitFactory.getConn().createChannel();
                    //创建exchange
                    this.channel.exchangeDeclare(RabbitMqContent.EXCHANGE_NAME, "direct", false, false, null);
                    //创建队列
                    this.channel.queueDeclare(RabbitMqContent.INTERFACE_CALL_INFO, false, false, false, null);
                    //绑定exchange和queue
                    this.channel.queueBind(RabbitMqContent.INTERFACE_CALL_INFO, RabbitMqContent.EXCHANGE_NAME, RabbitMqContent.INTERFACE_CALL_INFO);
                }
            }
        }
        InterfaceCallInfo interfaceCallInfo = ServiceUtil.getInterfaceCallInfo(className, methodName, ServiceCode.SUCCESS.getText().equals(sr.getServiceCode()), runTime, jvmUniqueMark);
        RabbitUtils.sendInterfaceCallInfo(interfaceCallInfo, channel);

        return proceed;
    }
}
