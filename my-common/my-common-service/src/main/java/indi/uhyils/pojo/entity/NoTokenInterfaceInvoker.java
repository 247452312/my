package indi.uhyils.pojo.entity;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.annotation.NoLogin;
import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.pojo.DTO.LoginDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.BlankCommand;
import indi.uhyils.util.AopUtil;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.RpcApiUtil;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月21日 08时19分
 */
public class NoTokenInterfaceInvoker extends AbstractAnnotationInterfaceInvoker {


    public NoTokenInterfaceInvoker(ProceedingJoinPoint pjp) {
        super(pjp);
    }

    public static NoTokenInterfaceInvoker create(ProceedingJoinPoint pjp) {
        return new NoTokenInterfaceInvoker(pjp);
    }


    /**
     * 检查是否是公开接口
     *
     * @param pjp
     *
     * @return
     */
    public static boolean checkAnnotation(ProceedingJoinPoint pjp) {
        final Class<?> targetClass = pjp.getTarget().getClass();
        Signature signature = pjp.getSignature();
        NoLogin[] methodNoLoginAnnotation = ((MethodSignature) signature).getMethod().getAnnotationsByType(NoLogin.class);
        NoLogin[] classNoLoginAnnotation = targetClass.getAnnotationsByType(NoLogin.class);
        return CollectionUtil.isNotEmpty(methodNoLoginAnnotation) || CollectionUtil.isNotEmpty(classNoLoginAnnotation);
    }


    @Override
    public Object invoke() throws Throwable {
        //获取token
        DefaultCQE arg = AopUtil.getDefaultCQEInPjp(pjp);
        String token = arg.getToken();

        // 未登录,判断为游客第一次访问,生成token
        if (StringUtils.isEmpty(token) && arg.getUser() == null) {
            final LoginDTO loginDTO = visiterLogin();
            UserInfoHelper.setUser(loginDTO.getUserEntity());
            UserInfoHelper.setToken(loginDTO.getToken());
        } else if (arg.getUser() != null) {
            UserInfoHelper.setUser(arg.getUser());
        } else if (StringUtils.isNotEmpty(token)) {
            UserInfoHelper.setToken(token);
        }
        return pjp.proceed();

    }


    /**
     * 游客登录
     *
     * @return 登录结果
     */
    private LoginDTO visiterLogin() throws Throwable {
        BlankCommand build = new BlankCommand();
        ArrayList<Object> args = new ArrayList<>();
        args.add(build);
        final Object o1 = RpcApiUtil.rpcApiTool("UserProvider", "visiterLogin", args);
        JSONObject o = (JSONObject) o1;
        return o.toJavaObject(LoginDTO.class);
    }

}
