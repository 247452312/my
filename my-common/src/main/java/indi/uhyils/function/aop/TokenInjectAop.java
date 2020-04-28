package indi.uhyils.function.aop;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.UserTypeEnum;
import indi.uhyils.model.TokenInfo;
import indi.uhyils.model.UserEntity;
import indi.uhyils.request.DefaultRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.util.DubboApiUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月27日 16时32分
 */
@Component
@Aspect
@Order(2)
public class TokenInjectAop {

    /**
     * 自定义日志
     */
    private Logger logger = LoggerFactory.getLogger(TokenInjectAop.class);

    /**
     * 定义切入点，切入点为indi.uhyils.serviceImpl包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * indi.uhyils.serviceImpl.*.*(..)))")
    public void tokenInjectPoint() {
    }


    @Around("tokenInjectPoint()")
    public Object tokenInjectAroundAspect(ProceedingJoinPoint pjp) throws Throwable {

        //NoToken结尾的方法直接放行 不需要token
        String methodName = pjp.getSignature().getName();
        if (methodName.endsWith("NoToken")) {
            //执行方法
            Object proceed = pjp.proceed();
            return proceed;
        }


        //注入defaultRequest中的userId
        //TODO 没有防止下标越界
        //获取token
        Object[] objs = pjp.getArgs();
        DefaultRequest arg = (DefaultRequest) objs[0];
        String token = arg.getToken();

        //解析token获取tokeninfo
        TokenInfo tokenInfo = getTokenInfo(token);

        //根据tokeninfo 获取UserEntity
        UserTypeEnum type = tokenInfo.getType();
        UserEntity userEntity;
        if (type != UserTypeEnum.TOURIST) { //如果不等于 则说明不是游客 -> 数据库中有数据
            ArrayList<Object> args = new ArrayList<>();
            args.add(IdRequest.build(tokenInfo.getUserId()));
            ServiceResult<UserEntity> serviceResult = DubboApiUtil.dubboApiTool("UserService", "getUserByIdNoToken", args);
            userEntity = serviceResult.getData();
        } else {
            userEntity = new UserEntity();
            userEntity.setId(tokenInfo.getUserId());
            userEntity.setNickName("游客_" + tokenInfo.getUserId());
        }
        arg.setUser(userEntity);

        //执行方法
        Object proceed = pjp.proceed(objs);
        return proceed;
    }

    private TokenInfo getTokenInfo(String token) throws ClassNotFoundException {
        DefaultRequest request = new DefaultRequest();
        request.setToken(token);
        List list = new ArrayList();
        list.add(request);
        ServiceResult serviceResult = DubboApiUtil.dubboApiTool("UserService", "getTokenInfoByTokenNoToken", list);
        JSONObject data = (JSONObject) serviceResult.getData();
        TokenInfo tokenInfo = data.toJavaObject(TokenInfo.class);
        return tokenInfo;
    }


}
