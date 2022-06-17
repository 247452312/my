package indi.uhyils.aop;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.annotation.NoToken;
import indi.uhyils.context.MyContext;
import indi.uhyils.context.UserContext;
import indi.uhyils.enums.ServiceCode;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.CheckUserHavePowerQuery;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.util.AopUtil;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.RpcApiUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
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
 * token转用户信息切面(包含验证)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月27日 16时32分
 */
@Component
@Aspect
@Order(20)
public class TokenInjectAop {


    /**
     * service后缀
     */
    private static final String IMPL = "Impl";

    /**
     * 超级管理员账号
     */
    private static final String ADMIN = "admin";

    @Autowired
    private RedisPoolHandle redisPoolHandle;

    /**
     * 定义切入点，切入点为service包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public indi.uhyils.pojo.DTO.base.ServiceResult indi.uhyils.protocol..*.*(..)))")
    public void tokenInjectPoint() {
    }


    /**
     * 1. 如果方法有NoToken注解,直接放行,不需要user注入
     * 2. 查询用户是否存在, 如果存在 就不需要检查token
     * 3. 如果没有user 根据token获取用户 检查用户是否存在
     * 4. 超级管理员直接放行
     * 5. 查询用户是否有权限
     * 6. 查询正常用户是否登录
     *
     * @param pjp pjp
     *
     * @return pjp 的返回值
     *
     * @throws Throwable pjp执行出错
     */
    @Around("tokenInjectPoint()")
    public Object tokenInjectAroundAspect(ProceedingJoinPoint pjp) throws Throwable {

        //NoToken注释的方法直接放行 不需要token
        Class<?> targetClass = pjp.getTarget().getClass();
        String className = targetClass.getCanonicalName();
        String methodName = pjp.getSignature().getName();

        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        NoToken[] methodNoTokenAnnotation = targetMethod.getAnnotationsByType(NoToken.class);
        NoToken[] classNoTokenAnnotation = targetClass.getAnnotationsByType(NoToken.class);
        if (CollectionUtil.isNotEmpty(methodNoTokenAnnotation) || CollectionUtil.isNotEmpty(classNoTokenAnnotation)) {
            //执行方法
            return pjp.proceed();
        }

        //获取token
        DefaultCQE arg = AopUtil.getDefaultCQEInPjp(pjp);
        String token = arg.getToken();


        /* 查询有没有登录 */
        if (StringUtils.isEmpty(token) && arg.getUser() == null) {
            return ServiceResult.buildNoLoginResult();
        }

        /* 查询是否超时 */
        UserDTO userDTO;
        // 如果参数中携带了用户,则不需要去再次查询用户
        if (arg.getUser() != null) {
            userDTO = arg.getUser();
        } else {
            userDTO = redisPoolHandle.getUser(token);
        }
        if (userDTO == null) {
            return ServiceResult.buildLoginOutResult();
        }
        UserContext.setUser(userDTO);
        try {

            /* 查询是否有权限 */
            // 超级管理员直接放行
            if (ADMIN.equals(userDTO.getUsername())) {
                userDTO.setRoleId(MyContext.ADMIN_ROLE_ID);
                arg.setUser(userDTO);
                //执行方法
                return pjp.proceed(new DefaultCQE[]{arg});
            }

            String substring = className.substring(className.lastIndexOf('.') + 1);
            if (substring.contains(IMPL)) {
                substring = substring.substring(0, substring.length() - 4);
            }
            ServiceResult checkUserHavePowerServiceResult = checkUserHavePower(userDTO, userDTO.getId(), substring, methodName, token, arg);
            if (!ServiceCode.SUCCESS.getText().equals(checkUserHavePowerServiceResult.getServiceCode())) {
                return checkUserHavePowerServiceResult;
            }
            Boolean havePower = (Boolean) checkUserHavePowerServiceResult.getData();
            if (!havePower) {
                return ServiceResult.buildNoAuthResult();
            }

            arg.setUser(userDTO);
            //执行方法
            return pjp.proceed(new DefaultCQE[]{arg});
        } finally {
            UserContext.clean();
        }
    }

    /**
     * 检查指定用户有没有指定权限
     *
     * @param id            用户id
     * @param interfaceName 权限接口名
     * @param methodName    权限方法名
     *
     * @return 是否有权限
     */
    private ServiceResult<JSONObject> checkUserHavePower(UserDTO userEntity, Long id, String interfaceName, String methodName, String token, DefaultCQE request) {
        CheckUserHavePowerQuery build = CheckUserHavePowerQuery.build(interfaceName, methodName, id);
        build.setToken(token);
        build.setUser(userEntity);
        ArrayList<Object> args = new ArrayList<>();
        args.add(build);
        JSONObject o = (JSONObject) RpcApiUtil.rpcApiTool("PowerProvider", "checkUserHavePower", args);
        return o.toJavaObject(ServiceResult.class);
    }
}
