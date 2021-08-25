package indi.uhyils.util;

import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Aop中公用的类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月12日 09时52分
 */
public class AopUtil {

    private AopUtil() {
    }

    /**
     * 从切点中获取defaultRequest
     *
     * @param pjp 切点
     *
     * @return 默认请求
     *
     * @throws Exception 参数错误
     */
    public static DefaultRequest getDefaultRequestInPjp(ProceedingJoinPoint pjp) throws Exception {
        Object[] objs = pjp.getArgs();
        boolean b = objs == null;
        boolean b1 = b || objs.length == 0;
        //如果没有参数
        if (b || b1) {
            throw new Exception("访问请求无参数");
        }
        return (DefaultRequest) objs[0];
    }
}
