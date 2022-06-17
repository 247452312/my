package indi.uhyils.aop;

import indi.uhyils.context.MyContext;
import indi.uhyils.enums.ServiceCode;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.Action;
import indi.uhyils.pojo.DTO.request.AddBlackIpRequest;
import indi.uhyils.pojo.DTO.request.GetLogIntervalByIpQuery;
import indi.uhyils.pojo.DTO.response.WebResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.BlackListProvider;
import indi.uhyils.redis.OffLineJedis;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.redis.Redisable;
import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.util.DefaultCQEBuildUtil;
import indi.uhyils.util.LogPushUtils;
import indi.uhyils.util.LogUtil;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 爬虫防范
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月23日 09时43分
 */
@Component
@Aspect
public class IpSpiderTableAspect {

    /**
     * 黑名单 在redis中的key
     *
     * @ps 在redis中 黑名单是set格式的
     */
    private static final String IP_BLACK_REDIS_KEY = "ip_black_redis_key";

    /**
     * 临时冻结等级 在redis中的key
     *
     * @ps hash结构 key为ip value为冻结等级
     */
    private static final String IP_BLACK_TEMP_FROZEN_LEVEL_REDIS_KEY = "ip_black_temp_frozen_level_redis_key";

    /**
     * 临时冻结到期时间 在redis中的key
     *
     * @ps hash结构 key为ip value为冻结开始时间
     */
    private static final String IP_BLACK_TEMP_FROZEN_TIME_OUT_REDIS_KEY = "ip_black_temp_frozen_time_out_redis_key";

    /**
     * 错误次数
     *
     * @ps hash结构 key:ip,value:count
     */
    private static final String IP_TEMP_RECORD_KEY_COUNT = "ip_temp_record_key_count";

    /**
     * 最大错误次数
     */
    private static final Integer WRONG_COUNT = 3;

    /**
     * 首次被冻结时间
     */
    private static final Long FIRST_FROZEN_TIME = 3 * 60 * 1000L;

    /**
     * 第二次冻结时间
     */
    private static final Long SECOND_FROZEN_TIME = 60 * 60 * 1000L;

    /**
     * 最大冻结次数 如果第三次冻结,则清除冻结次数并加入永久黑名单
     */
    private static final Long MAX_FROZEN_COUNT = 2L;


    @RpcReference
    private BlackListProvider blackListService;

    @Autowired
    private RedisPoolHandle redisPoolHandle;

    /**
     * 黑名单是否初始化
     */
    private volatile Boolean init = Boolean.FALSE;

    /**
     * 是否可以初始化
     */
    private volatile Boolean canInit = Boolean.TRUE;

    /**
     * 定义切入点，切入点为 {@link indi.uhyils.controller.AllController#action(Action, HttpServletRequest)}
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * indi.uhyils.controller.AllController.action(..)))")
    public void ipSpiderTableAspectPoint() {
    }

    /**
     * 初始化redis中的黑名单
     *
     * @return 是否成功 不成功的原因是多方面的, 首先 redis没有开, 其次 log服务没有开
     */
    @PostConstruct
    private boolean init() {
        // 没有初始化并且可以初始化
        if (Boolean.FALSE.equals(init) && Boolean.TRUE.equals(canInit)) {
            synchronized (this) {
                if (Boolean.FALSE.equals(init) || Boolean.TRUE.equals(canInit)) {
                    try {
                        // 去后台获取数据库中的ip黑名单
                        DefaultCQE request = DefaultCQEBuildUtil.getAdminDefaultCQE();
                        ServiceResult<List<String>> allIpBlackList = blackListService.getAllIpBlackList(request);
                        // 如果log服务挂了
                        if (allIpBlackList == null || !allIpBlackList.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
                            // 设置为不可用
                            canInit = Boolean.FALSE;
                            return Boolean.FALSE;
                        }
                        // 将黑名单存入redis
                        Long sadd = redisPoolHandle.sadd(IP_BLACK_REDIS_KEY, allIpBlackList.getData());
                        // 如果redis不可用
                        if (sadd == -1L) {
                            canInit = Boolean.FALSE;
                            return Boolean.FALSE;
                        }
                        init = Boolean.TRUE;
                        return Boolean.TRUE;
                    } catch (Exception e) {
                        LogUtil.error(this, e);
                        canInit = Boolean.FALSE;
                        return Boolean.FALSE;
                    }
                }
            }
        }
        return Boolean.TRUE;

    }

    @Around("ipSpiderTableAspectPoint()")
    public Object ipSpiderTableAspectAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Action action = (Action) args[0];
        HttpServletRequest request = (HttpServletRequest) args[1];
        /*初始化*/
        // 如果初始化没有成功 或者已经初始化过 但是失败了
        if (!(init && canInit)) {
            return pjp.proceed();
        }
        //如果是获取验证码的请求,忽略
        if (action.getInterfaceName().equals(MyContext.VERIFICATION_CODE_INTERFACE) && action.getMethodName().equals(MyContext.GET_VERIFICATION_CODE_METHOD)) {
            return pjp.proceed();
        }
        Redisable jedis = redisPoolHandle.getRedisPool().getJedis();
        // 如果redis没有开启,此功能默认不开启
        if (jedis instanceof OffLineJedis) {
            return pjp.proceed();
        }
        try {
            // 获取设备的ip
            String ip = LogPushUtils.getIpAddress(request);

            /*检查临时冻结列表 以及黑名单列表*/
            // 是否在黑名单中
            Boolean contains = jedis.sismember(IP_BLACK_REDIS_KEY, ip);
            if (contains) {
                return WebResponse.build(null, ServiceCode.REFUSE_VISIT);
            }
            /* 是否在临时禁用名单中*/
            String hget = jedis.hget(IP_BLACK_TEMP_FROZEN_LEVEL_REDIS_KEY, ip);
            long level = Long.parseLong(hget == null ? "0" : hget);
            // 查询此用户有没有冻结等级
            if (level != 0) {
                String outTimeStr = jedis.hget(IP_BLACK_TEMP_FROZEN_TIME_OUT_REDIS_KEY, ip);
                long timeEnd = Long.parseLong(outTimeStr == null ? "0" : outTimeStr);
                // 如果冻结时间没有过
                if (timeEnd > System.currentTimeMillis()) {
                    return WebResponse.build(level, ServiceCode.FROZEN_TEMP);
                }
            }
            /*查询此ip是否被临时记录*/
            Boolean tempRecord = jedis.hexists(IP_TEMP_RECORD_KEY_COUNT, ip);
            // 如果被记录
            if (tempRecord) {
                /*验证 验证码 是否正确*/
                String interfaceName = action.getInterfaceName();
                String methodName = action.getMethodName();
                if (interfaceName.equals(MyContext.VERIFICATION_CODE_INTERFACE) && methodName.equals(MyContext.VERIFICATION_CODE_METHOD)) {
                    WebResponse proceed = (WebResponse) pjp.proceed();
                    Boolean data = (Boolean) proceed.getData();
                    // 验证码验证失败
                    if (!data) {
                        return faultVerification(jedis, ip);
                    }
                    /*验证码验证成功*/
                    //清空验证码错误次数
                    jedis.hdel(IP_TEMP_RECORD_KEY_COUNT, ip);
                    // 这里没法返回上一个页面,只能返回验证码验证成功的逻辑
                    return proceed;
                }
                return faultVerification(jedis, ip);
            }
            // 利用Completable 异步执行真实的业务
            CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    return pjp.proceed();
                } catch (Throwable throwable) {
                    LogUtil.error(this, throwable);
                    // 这里是绝对不会执行的,除非服务不存在
                    return null;
                }
            });
            GetLogIntervalByIpQuery defaultRequest = new GetLogIntervalByIpQuery();
            UserDTO user = new UserDTO();
            user.setId(MyContext.ADMIN_USER_ID);
            user.setUsername("admin");
            defaultRequest.setUser(user);
            defaultRequest.setIp(ip);
            ServiceResult<Boolean> logIntervalByIp = blackListService.getLogIntervalByIp(defaultRequest);
            // 如果log服务挂了
            if (!logIntervalByIp.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
                return completableFuture.get();
            }

            // 如果不是爬虫
            if (!logIntervalByIp.getData()) {
                return completableFuture.get();
            }
            jedis.hincrby(IP_TEMP_RECORD_KEY_COUNT, ip);
            return WebResponse.build(null, ServiceCode.SPIDER_VERIFICATION);
        } catch (Throwable throwable) {
            LogUtil.error(this, throwable);
            return WebResponse.build(null, ServiceCode.ERROR);
        } finally {
            jedis.close();
        }
    }

    /**
     * 输入了错误的验证码 或者根本就没有输入验证码
     *
     * @param ip ip
     *
     * @return 返回前端的东西
     */
    private Object faultVerification(Redisable jedis, String ip) {
        // 获取自增后的验证码输入错误的次数
        Long count = jedis.hincrby(IP_TEMP_RECORD_KEY_COUNT, ip);
        // 错误超过一定的次数
        if (count > WRONG_COUNT) {
            long now = System.currentTimeMillis();
            // 获取此次被冻结的次数
            long frozenCount = jedis.hincrby(IP_BLACK_TEMP_FROZEN_LEVEL_REDIS_KEY, ip);
            String outTime = jedis.hget(IP_BLACK_TEMP_FROZEN_TIME_OUT_REDIS_KEY, ip);
            /* 如果之前是冻结等级为1 并且已经过了冻结结束时间3分钟,设置冻结等级为1
             *  如果之前冻结等级为2 并且已经过了冻结结束时间 1小时 设置冻结等级为1*/
            if (frozenCount == 2 && now - Long.parseLong(outTime) > FIRST_FROZEN_TIME) {
                jedis.hset(IP_BLACK_TEMP_FROZEN_LEVEL_REDIS_KEY, ip, "1");
                jedis.hset(IP_BLACK_TEMP_FROZEN_TIME_OUT_REDIS_KEY, ip, Long.toString(now + FIRST_FROZEN_TIME));
            } else if (frozenCount == 3 && now - Long.parseLong(outTime) > SECOND_FROZEN_TIME) {
                jedis.hset(IP_BLACK_TEMP_FROZEN_LEVEL_REDIS_KEY, ip, "1");
                jedis.hset(IP_BLACK_TEMP_FROZEN_TIME_OUT_REDIS_KEY, ip, Long.toString(now + FIRST_FROZEN_TIME));
            } else if (frozenCount > MAX_FROZEN_COUNT) {
                // 加入永久黑名单
                AddBlackIpRequest build = AddBlackIpRequest.build(ip);
                UserDTO user = new UserDTO();
                user.setId(MyContext.ADMIN_USER_ID);
                build.setUser(user);
                blackListService.addBlackIp(build);
                // 清空临时冻结
                jedis.hdel(IP_BLACK_TEMP_FROZEN_LEVEL_REDIS_KEY, ip);
                jedis.hdel(IP_BLACK_TEMP_FROZEN_TIME_OUT_REDIS_KEY, ip);
                /*被冻结了两次,但是这个ip无知无觉,继续访问数据,一眼就看出他不是人, 大威天龙!!!*/
                return WebResponse.build(null, ServiceCode.REFUSE_VISIT);
            } else if (frozenCount == 1) {
                //冻结第一个等级 (3分钟)
                jedis.hset(IP_BLACK_TEMP_FROZEN_TIME_OUT_REDIS_KEY, ip, Long.toString(System.currentTimeMillis() + FIRST_FROZEN_TIME));
            } else if (frozenCount == 2) {
                //冻结第二个等级 (一小时)
                jedis.hset(IP_BLACK_TEMP_FROZEN_TIME_OUT_REDIS_KEY, ip, Long.toString(System.currentTimeMillis() + SECOND_FROZEN_TIME));
            }
            //告知前端您已被冻结以及冻结等级
            return WebResponse.build(frozenCount, ServiceCode.FROZEN_TEMP);
        }
        // 验证爬虫
        return WebResponse.build(null, ServiceCode.SPIDER_VERIFICATION);
    }

}
