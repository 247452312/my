package indi.uhyils.aop;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import indi.uhyils.content.RabbitMqContent;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.exception.NoRequestLinkException;
import indi.uhyils.pojo.mqinfo.InterfaceCallInfo;
import indi.uhyils.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.pojo.rabbit.RabbitFactory;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.util.AopUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.RabbitUtils;
import indi.uhyils.util.ServiceUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 07时45分
 */
@Component
@Aspect
@Order(40)
public class TimeLogAop {


    /**
     * 定义切入点，切入点为indi.uhyils.serviceImpl包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * indi.uhyils.serviceImpl.*.*(..)))")
    public void logAspectPoint() {
    }

    @Autowired
    private RabbitFactory rabbitFactory;

    @Autowired
    private JvmUniqueMark jvmUniqueMark;

    private Channel channel;

    /**
     * 日志显示
     * 添加链路跟踪
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("logAspectPoint()")
    public Object timeLogAroundAspect(ProceedingJoinPoint pjp) throws Throwable {

        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();

        //添加链路跟踪
        DefaultRequest arg = AopUtil.getDefaultRequestInPjp(pjp);
        LinkNode<String> requestLink = arg.getRequestLink();
        if (requestLink == null) {
            throw new NoRequestLinkException();
        }
        LinkNode<String> temp = requestLink;
        while (temp.hasNext()) {
            temp = temp.getLinkNode();
        }
        LinkNode<String> next = new LinkNode<>();
        next.setData(String.format("%s : %s", className, methodName));
        temp.setLinkNode(next);

        //方法执行前显示 类名,方法名,参数名
        before(pjp, className, methodName);
        Long startTime = System.currentTimeMillis();

        //执行方法
        Object proceed = pjp.proceed();

        Long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;

        after(className, methodName, runTime / 1000.0, proceed);
        ServiceResult sr = (ServiceResult) proceed;

        if (channel == null) {
            synchronized (rabbitFactory) {
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


    /**
     * 日志切面后
     *
     * @param className  类名称
     * @param methodName 方法名称
     * @param v          执行时间
     * @param proceed
     */
    private void after(String className, String methodName, double v, Object proceed) {
        LogUtil.info(this, String.format("方法执行完毕:  %s类中的%s,执行时间为%f秒", className, methodName, v));
        LogUtil.info(this, String.format("   返回值为:%s", JSONObject.toJSONString(proceed)));
    }

    /**
     * 日志切面前
     *
     * @param pjp        切点类,没啥说的
     * @param className  类名
     * @param methodName 方法名
     */
    private void before(ProceedingJoinPoint pjp, String className, String methodName) {
        StringBuilder sb = new StringBuilder();
        Object[] args = pjp.getArgs();
        sb.append("方法开始执行:  ");
        sb.append(className);
        sb.append("类中的");
        sb.append(methodName);
        sb.append(",参数为:");
        for (Object arg : args) {
            sb.append(JSONObject.toJSONString(arg));
            sb.append("(");
            sb.append(arg.getClass().getSimpleName());
            sb.append(")");
        }
        LogUtil.info(this, "---------------------↓-↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓------------------------");
        LogUtil.info(this, sb.toString());
        sb.delete(0, sb.length());
    }
}
