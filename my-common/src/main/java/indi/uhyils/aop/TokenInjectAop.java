package indi.uhyils.aop;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.ResponseCode;
import indi.uhyils.pojo.model.DeptEntity;
import indi.uhyils.pojo.model.PowerEntity;
import indi.uhyils.pojo.model.TokenInfo;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.util.AopUtil;
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
@Order(20)
public class TokenInjectAop {

    /**
     * 自定义日志
     */
    private Logger logger = LoggerFactory.getLogger(TokenInjectAop.class);

    /**
     * 放行方法后缀
     */
    private static final String RELEASE_SUFFIX = "NoToken";

    /**
     * 定义切入点，切入点为indi.uhyils.serviceImpl包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * indi.uhyils.serviceImpl.*.*(..)))")
    public void tokenInjectPoint() {
    }


    /**
     * token 验证有无
     * token 解析
     * 转为userEntity注入
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("tokenInjectPoint()")
    public Object tokenInjectAroundAspect(ProceedingJoinPoint pjp) throws Throwable {

        //NoToken结尾的方法直接放行 不需要token
        String className = pjp.getTarget().getClass().getCanonicalName();
        String methodName = pjp.getSignature().getName();

        if (methodName.endsWith(RELEASE_SUFFIX)) {
            //执行方法
            return pjp.proceed();
        }

        //获取token
        DefaultRequest arg = AopUtil.getDefaultRequestInPjp(pjp);
        String token = arg.getToken();
        /* 查询有没有登录 */
        if (token == null || "".equals(token)) {
            return ServiceResult.buildFailedResult("请登录!", null, arg);
        }
        /* 查询是否超时 */
        //解析token获取tokenInfo
        ServiceResult getTokenInfoByTokenNoToken = parseToken(token, arg);
        if (!ResponseCode.SUCCESS.getText().equals(getTokenInfoByTokenNoToken.getServiceCode())) { //解析出现异常
            return getTokenInfoByTokenNoToken;
        }
        JSONObject data = (JSONObject) getTokenInfoByTokenNoToken.getData();
        final TokenInfo tokenInfo = data.toJavaObject(TokenInfo.class);
        if (tokenInfo.getTimeOut()) { //redis中token超时
            return ServiceResult.buildFailedResult("登录超时,请重新登录", null, arg);
        }
        /* 查询是否有权限 */
        ServiceResult<JSONObject> serviceResult = getUserByIdNoToken(token, tokenInfo, arg);
        // 查询是否有报错,如果报错.则结束请求
        if (!ResponseCode.SUCCESS.getText().equals(serviceResult.getServiceCode())) {
            return serviceResult;
        }
        UserEntity userEntity = serviceResult.getData().toJavaObject(UserEntity.class);
        boolean haveAuth = false;
        PowerEntity powerEntity = new PowerEntity();
        powerEntity.setInterfaceName(className);
        powerEntity.setMethodName(methodName);
        for (DeptEntity dept : userEntity.getRole().getDepts()) {
            if (dept.getPowers().contains(powerEntity)) {
                haveAuth = true;
                break;
            }
        }
        if (!haveAuth) {
            return ServiceResult.buildFailedResult("没有权限", null, arg);
        }

        arg.setUser(userEntity);
        //执行方法
        Object proceed = pjp.proceed(new DefaultRequest[]{arg});
        return proceed;
    }
// TODO 如果此截面没问题 删了这段
//    private ServiceResult<String> getUserTokenNoToken(String touristId, DefaultRequest request) throws ClassNotFoundException {
//        GetUserRequest getUserRequest = new GetUserRequest();
//        getUserRequest.setUserType(UserTypeEnum.TOURIST);
//        getUserRequest.setId(MD5Util.MD5Encode(touristId));
//        getUserRequest.setRequestLink(request.getRequestLink());
//        List list = new ArrayList();
//        list.add(getUserRequest);
//        ServiceResult<String> stringServiceResult = DubboApiUtil.dubboApiTool("UserService", "getUserTokenNoToken", list, request);
//        return stringServiceResult;
//    }


    private ServiceResult<JSONObject> getUserByIdNoToken(String token, TokenInfo tokenInfo, DefaultRequest request) throws ClassNotFoundException {
        ArrayList<Object> args = new ArrayList<>();
        IdRequest build = IdRequest.build(tokenInfo.getUserId());
        build.setToken(token);
        build.setRequestLink(request.getRequestLink());
        args.add(build);
        ServiceResult<JSONObject> jsonObjectServiceResult = DubboApiUtil.dubboApiTool("UserService", "getUserByIdNoToken", args, request);
        return jsonObjectServiceResult;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     * @throws ClassNotFoundException
     */
    private ServiceResult parseToken(String token, DefaultRequest request) throws ClassNotFoundException {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken(token);
        defaultRequest.setRequestLink(request.getRequestLink());
        List list = new ArrayList();
        list.add(defaultRequest);
        ServiceResult serviceResult = DubboApiUtil.dubboApiTool("UserService", "getTokenInfoByTokenNoToken", list, request);
        return serviceResult;
    }


}
