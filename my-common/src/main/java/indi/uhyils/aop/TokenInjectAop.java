package indi.uhyils.aop;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.ResponseCode;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.model.base.TokenInfo;
import indi.uhyils.pojo.request.CheckUserHavePowerRequest;
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
     * service后缀
     */
    private static final String IMPL = "Impl";
    /**
     * 放行方法后缀
     */
    private static final String RELEASE_SUFFIX = "NoToken";

    /**
     * 超级管理员账号
     */
    private static final String ADMIN = "admin";


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
     * @param pjp pjp
     * @return pjp 的返回值
     * @throws Throwable pjp执行出错
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
        ServiceResult<JSONObject> getTokenInfoByTokenNoToken = parseToken(token, arg);
        //解析出现异常
        if (!ResponseCode.SUCCESS.getText().equals(getTokenInfoByTokenNoToken.getServiceCode())) {
            return getTokenInfoByTokenNoToken;
        }
        JSONObject data = getTokenInfoByTokenNoToken.getData();
        final TokenInfo tokenInfo = data.toJavaObject(TokenInfo.class);
        //redis中token超时
        if (tokenInfo.getTimeOut()) {
            return ServiceResult.buildFailedResult("登录超时,请重新登录", null, arg);
        }

        /* 查询是否有权限 */
        UserEntity userEntity;
        // 如果参数中携带了用户,则不需要去再次查询用户
        if (arg.getUser() != null) {
            userEntity = arg.getUser();
        } else {
            ServiceResult<JSONObject> serviceResult = getUserByIdNoToken(token, tokenInfo, arg);
            // 查询是否有报错,如果报错.则结束请求
            if (!ResponseCode.SUCCESS.getText().equals(serviceResult.getServiceCode())) {
                return serviceResult;
            }
            userEntity = serviceResult.getData().toJavaObject(UserEntity.class);
        }

        // 超级管理员直接放行
        if (ADMIN.equals(userEntity.getUserName())) {
            arg.setUser(userEntity);
            //执行方法
            return pjp.proceed(new DefaultRequest[]{arg});
        }

        String substring = className.substring(className.lastIndexOf('.') + 1);
        if (substring.contains(IMPL)) {
            substring = substring.substring(0, substring.length() - 4);
        }
        ServiceResult checkUserHavePowerServiceResult = checkUserHavePower(userEntity.getId(), substring, methodName, token, arg);
        if (!ResponseCode.SUCCESS.getText().equals(checkUserHavePowerServiceResult.getServiceCode())) {
            return checkUserHavePowerServiceResult;
        }
        Boolean havePower = (Boolean) checkUserHavePowerServiceResult.getData();
        if (!havePower) {
            return ServiceResult.buildFailedResult("没有权限", null, arg);
        }

        arg.setUser(userEntity);
        //执行方法
        return pjp.proceed(new DefaultRequest[]{arg});
    }

    /**
     * 检查指定用户有没有指定权限
     *
     * @param id            用户id
     * @param interfaceName 权限接口名
     * @param methodName    权限方法名
     * @return 是否有权限
     */
    private ServiceResult<JSONObject> checkUserHavePower(String id, String interfaceName, String methodName, String token, DefaultRequest request) {
        CheckUserHavePowerRequest build = CheckUserHavePowerRequest.build(interfaceName, methodName, id);
        build.setToken(token);
        build.setRequestLink(request.getRequestLink());
        ArrayList<Object> args = new ArrayList<>();
        args.add(build);
        return DubboApiUtil.dubboApiTool("PowerService", "checkUserHavePowerNoToken", args, request);
    }

    private ServiceResult<JSONObject> getUserRoleByRoleId(String token, String roleId, DefaultRequest arg) {
        IdRequest build = IdRequest.build(roleId);
        build.setToken(token);
        build.setRequestLink(arg.getRequestLink());
        ArrayList<Object> args = new ArrayList<>();
        args.add(build);
        return DubboApiUtil.dubboApiTool("RoleService", "getRoleByRoleIdNoToken", args, arg);
    }


    private ServiceResult<JSONObject> getUserByIdNoToken(String token, TokenInfo tokenInfo, DefaultRequest request) {
        ArrayList<Object> args = new ArrayList<>();
        IdRequest build = IdRequest.build(tokenInfo.getUserId());
        build.setToken(token);
        build.setRequestLink(request.getRequestLink());
        args.add(build);
        return DubboApiUtil.dubboApiTool("UserService", "getUserByIdNoToken", args, request);
    }

    /**
     * 解析token
     *
     * @param token token
     * @return tokenInfo
     */
    private ServiceResult<JSONObject> parseToken(String token, DefaultRequest request) {
        DefaultRequest defaultRequest = new DefaultRequest();
        defaultRequest.setToken(token);
        defaultRequest.setRequestLink(request.getRequestLink());
        List<Object> list = new ArrayList<>();
        list.add(defaultRequest);
        return DubboApiUtil.dubboApiTool("UserService", "getTokenInfoByTokenNoToken", list, request);
    }


}
