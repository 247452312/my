package indi.uhyils.redis.aop;

import com.alibaba.fastjson.JSON;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.content.HotSpotContent;
import indi.uhyils.enum_.CacheTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.UserEntity;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.HotSpotResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.redis.hotspot.HotSpotRedisPool;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.MD5Util;
import indi.uhyils.util.ObjectByteUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 热点aop
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 08时24分
 */
@Component
@Aspect
@Order(50)
public class HotSpotAop {

    /**
     * redis写脚本
     */
    private static final String WRITE_LUA = "local keys = KEYS\n\nfor i, v in ipairs(keys) do\n   redis.call('HINCRBY','" + HotSpotContent.TABLES_HASH_KEY + "', keys[i], 1)\nend\nreturn true";
    /**
     * 检查缓存表是否更新
     */
    private static final String CHECK_TABLE_UPDATE = "local keys,val = KEYS,ARGV\n" +
            "for i, v in ipairs(val) do\n" +
            // 这里是防止不存在,给hashTable里赋初值为1
            "\tredis.call('hsetnx','" + HotSpotContent.TABLES_HASH_KEY + "',v,'1')\n" +
            "end\n" +
            "local k = 0\n" +
            "for i, v in ipairs(val) do\n" +
            "\tlocal table_version = redis.call('HGET',keys[1], v)\n" +
            "\tlocal real_table_version = redis.call('HGET','" + HotSpotContent.TABLES_HASH_KEY + "',v)\n" +
            "\tif not(real_table_version) or table_version ~= real_table_version then\n" +
            "\t\tk = 1\n" +
            "\tend\n" +
            "end  \n" +
            "if k == 0 then    \n" +
            "\treturn 1    \n" +
            "else    \n" +
            "\treturn 0    \n" +
            "end";
    /**
     * 更新缓存的lua脚本
     */
    private static final String UPDATE_CACHE = "local keys,val = KEYS,ARGV\n\nredis.call('hset',keys[1],keys[2],val[2])\nredis.call('hset',keys[1],keys[3],val[3])\n\nfor i, v in ipairs(redis.call('hkeys',val[1])) do\n\tredis.call('hset',keys[1],v,redis.call('hget',val[1],v))\nend";
    /**
     * 重试的间隔(ms)
     */
    private static final Long RETRY_INTERVAL = 1000L * 10;
    /**
     * 注解的ThreadLocal
     */
    private ThreadLocal<CacheTypeEnum> markThreadLocal = new ThreadLocal<>();
    @Autowired
    private RedisPoolHandle redisPoolHandle;
    @Autowired
    private HotSpotRedisPool hotSpotRedisPool;
    /**
     * 上次尝试的时间
     */
    private Long lastTryTime;

    /**
     * 定义切入点，切入点为indi.uhyils.serviceImpl包中的所有类的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public indi.uhyils.pojo.response.base.ServiceResult indi.uhyils.serviceImpl.*.*(..)) || execution(public indi.uhyils.pojo.response.base.ServiceResult indi.uhyils.service.base.DefaultEntityService.*(..))")
    public void hotSpotAspectPoint() {
    }

    @Around("hotSpotAspectPoint()")
    public Object hotSpotAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        // 如果 热点集群redis没有加载成功,则一段时间后自动重试一次
        if (!HotSpotRedisPool.initTypeIsRedis) {
            long lastTryTime = System.currentTimeMillis();
            if (this.lastTryTime == null) {
                // 失败后第一次调用
                this.lastTryTime = lastTryTime;
                return pjp.proceed();
            } else {
                // 判断是否超过一秒
                if (lastTryTime - this.lastTryTime > RETRY_INTERVAL) {
                    this.lastTryTime = lastTryTime;
                    Boolean initSuccess = hotSpotRedisPool.initPool();
                    if (!initSuccess) {
                        return pjp.proceed();
                    }
                } else {
                    return pjp.proceed();
                }
            }
        }
        //此接口的读写类型
        ReadWriteTypeEnum mark = null;
        //此接口指向的数据库表
        List<String> tables = new ArrayList<>();
        //此接口的热点缓存类型
        CacheTypeEnum cacheType = null;
        /*首先获取类上的注解*/
        Class<?> targetClass = pjp.getTarget().getClass();

        String className = targetClass.getSimpleName();
        String methodName = pjp.getSignature().getName();

        ReadWriteMark declaredAnnotation = targetClass.getDeclaredAnnotation(ReadWriteMark.class);
        if (declaredAnnotation != null) {
            mark = declaredAnnotation.type();
            tables.addAll(Arrays.asList(declaredAnnotation.tables()));
            cacheType = declaredAnnotation.cacheType();
        }
        /*再获取实现方法上的注解*/
        //获取所有的类以及父类
        Class<?> superclass = targetClass.getSuperclass();
        List<Class> classList = new ArrayList<>();
        classList.add(targetClass);
        while (superclass != Object.class) {
            classList.add(superclass);
            superclass = superclass.getSuperclass();
        }
        Method declaredMethod = null;
        for (Class clazz : classList) {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                String name = method.getName();
                if (name.equals(methodName)) {
                    declaredMethod = method;
                    break;
                }
            }
            if (declaredMethod != null) {
                break;
            }
        }
        if (declaredMethod != null) {
            ReadWriteMark methodDeclaredAnnotation = declaredMethod.getDeclaredAnnotation(ReadWriteMark.class);
            if (methodDeclaredAnnotation != null) {
                mark = methodDeclaredAnnotation.type();
                tables.addAll(Arrays.asList(methodDeclaredAnnotation.tables()));
                cacheType = methodDeclaredAnnotation.cacheType();
            }
        }
        /*首先查询此接口是写接口还是读接口*/
        if (mark == null) {
            //此处说明类上,接口上都没有此接口的信息,那么就正常执行,什么都不发生
            return pjp.proceed();
        } else {
            DefaultRequest arg = (DefaultRequest) pjp.getArgs()[0];
            UserEntity user = arg.getUser();
            switch (mark) {
                //此处是读接口应该做的方法
                case READ:
                    return doHotSpotRead(tables, className, methodName, cacheType, user, pjp);
                //此处是写接口应该用到的方法
                case WRITE:
                    markThreadLocal.set(cacheType);
                    Object proceed = pjp.proceed();
                    markThreadLocal.remove();
                    return proceed;
                default:
                    return pjp.proceed();
            }
        }
    }

    /**
     * 写接口应该做的方法
     */
    public void doHotSpotWrite(List<String> tables, CacheTypeEnum cacheType) {
        //如果此接口不允许缓存
        if (CacheTypeEnum.NOT_TYPE.equals(cacheType)) {
            return;
        }
        Jedis jedis = hotSpotRedisPool.getJedis();
        try {
            jedis.eval(WRITE_LUA, tables, new ArrayList<>());
        } finally {
            jedis.close();
        }

    }

    /**
     * 读接口应该做的方法
     */
    private Object doHotSpotRead(List<String> tables, String className, String methodName, CacheTypeEnum cacheType, UserEntity user, ProceedingJoinPoint pjp) throws Throwable {
        //如果此接口不允许缓存
        if (CacheTypeEnum.NOT_TYPE.equals(cacheType)) {
            return pjp.proceed();
        }

        Object arg = pjp.getArgs()[0];
        Class<?> aClass = arg.getClass();
        String simpleName = aClass.getSimpleName();
        String format;
        if (!DefaultRequest.class.getSimpleName().equals(simpleName)) {

            // Arrays.asList()不能add和addAll所以要new ArrayList()
            List<Field> fields = new ArrayList<>(Arrays.asList(aClass.getDeclaredFields()));
            while (aClass.getSuperclass() != Object.class) {
                aClass = aClass.getSuperclass();
                fields.addAll(Arrays.asList(aClass.getDeclaredFields()));
            }
            StringBuilder sb = new StringBuilder();
            for (Field field : fields) {
                // key中除去唯一标示,防止热点不起作用
                if ("unique".equals(field.getName())) {
                    continue;
                }
                field.setAccessible(Boolean.TRUE);
                sb.append(JSON.toJSONString(field.get(arg)));
            }
            // 获取热点redis中的key(有参数)
            format = String.format(HotSpotContent.HOTSPOT_HASH_KEY_ROLE, className, methodName, MD5Util.MD5Encode(sb.toString()));
        } else {
            // 获取热点redis中的key(无参数)
            format = String.format(HotSpotContent.HOTSPOT_HASH_KEY_ROLE, className, methodName, "none");
        }

        Jedis jedis = hotSpotRedisPool.getJedis();
        try {
            List<String> keys = new ArrayList<>();
            keys.add(format);
            //此处返回true为未更新,表示可以取缓存
            Object eval = jedis.eval(CHECK_TABLE_UPDATE, keys, tables);
            boolean canGetCache = (Long) eval == 1L;
            // 如果检查redis中的table更新了
            if (!canGetCache) {
                Object proceed = pjp.proceed();
                ServiceResult sr = (ServiceResult) proceed;
                // 如果接口不是成功,那么不进入缓存
                if (!ServiceCode.SUCCESS.getText().equals(sr.getServiceCode())) {
                    return proceed;
                }
                byte[] data = ObjectByteUtil.toByteArray(proceed);
                List<byte[]> updateKeys = new ArrayList<>();
                List<byte[]> updateArgv = new ArrayList<>();
                updateKeys.add(format.getBytes(StandardCharsets.UTF_8));
                updateArgv.add(HotSpotContent.TABLES_HASH_KEY.getBytes(StandardCharsets.UTF_8));

                updateKeys.add(HotSpotContent.CACHE_TYPE_MARK.getBytes(StandardCharsets.UTF_8));
                updateArgv.add(user.getRoleId().toString().getBytes(StandardCharsets.UTF_8));

                updateKeys.add(HotSpotContent.HOTSPOT_HASH_DATA_KEY.getBytes(StandardCharsets.UTF_8));
                updateArgv.add(data);

                jedis.eval(UPDATE_CACHE.getBytes(StandardCharsets.UTF_8), updateKeys, updateArgv);
                return proceed;
            }
            if (LogUtil.isDebugEnabled(HotSpotAop.class)) {
                LogUtil.debug(HotSpotAop.class, String.format("接口<%s> 读取redis中的缓存热点数据", methodName));
            }

            ServiceResult<HotSpotResponse> hotSpotResponse = ServiceResult.buildHotSpotHaveResult(format, HotSpotContent.HOTSPOT_HASH_DATA_KEY, (DefaultRequest) pjp.getArgs()[0]);
            return hotSpotResponse;
        } finally {
            jedis.close();
        }

    }

    public ThreadLocal<CacheTypeEnum> getMarkThreadLocal() {
        return markThreadLocal;
    }

}
