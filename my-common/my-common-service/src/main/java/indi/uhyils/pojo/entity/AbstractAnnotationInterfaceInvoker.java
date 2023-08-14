package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Nullable;
import indi.uhyils.context.SpiderContext;
import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.enums.ServiceCode;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.redis.RedisPool;
import indi.uhyils.redis.Redisable;
import indi.uhyils.util.SpringUtil;
import java.util.Optional;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月21日 08时20分
 */
public abstract class AbstractAnnotationInterfaceInvoker {

    /**
     * 接口信息
     */
    protected final ProceedingJoinPoint pjp;

    protected final RedisPool redisPool;

    protected AbstractAnnotationInterfaceInvoker(ProceedingJoinPoint pjp) {
        this.pjp = pjp;
        this.redisPool = SpringUtil.getBean(RedisPool.class);
    }

    /**
     * 执行此方法的结果
     *
     * @return
     *
     * @throws Throwable
     */
    public abstract Object invoke() throws Throwable;

    @Nullable
    protected ServiceResult<Long> checkIp() {
        Optional<String> userIp = UserInfoHelper.getUserIp();
        if (userIp.isPresent()) {
            String ip = userIp.get();
            try (final Redisable jedis = redisPool.getJedis()) {
                /*检查临时冻结列表 以及黑名单列表*/
                // 是否在黑名单中
                Boolean contains = jedis.sismember(SpiderContext.IP_BLACK_REDIS_KEY, ip);
                if (contains) {
                    return ServiceResult.buildResultByServiceCode(ServiceCode.REFUSE_VISIT);
                }
                /* 是否在临时禁用名单中*/
                String hget = jedis.hget(SpiderContext.IP_BLACK_TEMP_FROZEN_LEVEL_REDIS_KEY, ip);
                long level = Long.parseLong(hget == null ? "0" : hget);
                // 查询此用户有没有冻结等级
                if (level != 0) {
                    String outTimeStr = jedis.hget(SpiderContext.IP_BLACK_TEMP_FROZEN_TIME_OUT_REDIS_KEY, ip);
                    long timeEnd = Long.parseLong(outTimeStr == null ? "0" : outTimeStr);
                    // 如果冻结时间没有过
                    if (timeEnd > System.currentTimeMillis()) {
                        return ServiceResult.build(level, ServiceCode.FROZEN_TEMP);
                    }
                }
            }
        }
        return null;
    }

}
