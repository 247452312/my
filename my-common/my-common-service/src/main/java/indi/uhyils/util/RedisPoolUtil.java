package indi.uhyils.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.content.Content;
import indi.uhyils.pojo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.ArrayList;

/**
 * 懒加载,除了用到redis
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月04日 12时21分
 */
@Lazy
@Component
public class RedisPoolUtil {

    @Autowired
    private RedisPool redisPool;

    /**
     * 添加user到缓存 一共添加两个, 一个是 (token,userEntity->json) 一个是(userId,token) 都设置60分钟的过期时间
     * 缓存中的角色是没有权限的.也就是AOP中注入的user是没有记录权限集的
     *
     * @param token token
     * @param user  user
     */
    public void addUser(String token, UserEntity user) {
        Jedis jedis = redisPool.getJedis();
        try {
            String value = JSONObject.toJSONString(user);
            jedis.append(token, value);
            //半个小时
            jedis.expire(token, 60 * Content.LOGIN_TIME_OUT_MIN);
            jedis.append(user.getId(), token);
            jedis.expire(user.getId(), 60 * Content.LOGIN_TIME_OUT_MIN);

        } catch (JedisConnectionException e) {
            LogUtil.error(this, e);
        } finally {
            jedis.close();
        }

    }

    /**
     * 前台有操作都会经过这个方法, 通过token获取user, 同时刷新redis中用户的存在时间
     *
     * @param token token
     * @return user
     */
    public UserEntity getUser(String token) {
        Jedis jedis = redisPool.getJedis();
        try {
            String userJson = jedis.get(token);
            if (userJson == null || "".equals(userJson)) {
                return null;
            }
            UserEntity userEntity = JSON.parseObject(userJson, UserEntity.class);
            jedis.expire(token, 60 * Content.LOGIN_TIME_OUT_MIN);
            jedis.expire(userEntity.getId(), 60 * Content.LOGIN_TIME_OUT_MIN);
            return userEntity;
        } catch (JedisConnectionException e) {
            LogUtil.error(this, e);
        } finally {
            jedis.close();
        }
        return null;

    }

    public Boolean haveToken(String token) {
        Jedis jedis = redisPool.getJedis();
        try {
            Boolean exists = jedis.exists(token);
            return exists;
        } catch (JedisConnectionException e) {
            LogUtil.error(this, e);
        } finally {
            jedis.close();
        }
        return null;

    }

    public Boolean haveUserId(String userId) {
        Jedis jedis = redisPool.getJedis();
        try {
            Boolean exists = jedis.exists(userId);
            return exists;
        } catch (JedisConnectionException e) {
            LogUtil.error(this, e);
        } finally {
            jedis.close();
        }
        return null;

    }

    public boolean removeUserById(String userId) {
        Jedis jedis = redisPool.getJedis();
        try {
            String token = jedis.get(userId);
            Long del = jedis.del(userId, token);
            return del != 0;
        } catch (JedisConnectionException e) {
            LogUtil.error(this, e);
        } finally {
            jedis.close();
        }
        return true;
    }

    public boolean removeUserBytoken(String token) {
        Jedis jedis = redisPool.getJedis();
        try {
            String userJson = jedis.get(token);
            if (userJson == null || "".equals(userJson)) {
                return true;
            }
            UserEntity userEntity = JSON.parseObject(userJson, UserEntity.class);
            String id = userEntity.getId();
            Long del = jedis.del(id, token);
            return del != 0;
        } catch (JedisConnectionException e) {
            LogUtil.error(this, e);
        } finally {
            jedis.close();
        }
        return true;
    }


    public RedisPool getRedisPool() {
        return redisPool;
    }

    public void setRedisPool(RedisPool redisPool) {
        this.redisPool = redisPool;
    }


    private static final String REDIS_GET_LOCK_LUA = "" +
            "if(redis.call('exists',KEYS[1]) == 0) then " +
            "redis.call('hset',KEYS[1],ARGV[2],1);" +
            "redis.call('pexpire',KEYS[1],ARGV[1]);" +
            "return nil;" +
            "end;" +
            "if(redis.call('hexists',KEYS[1],ARGV[2]) == 1) then " +
            "redis.call('hincrby',KEYS[1],ARGV[2],1);" +
            "redis.call('pexpire',KEYS[1],ARGV[1]);" +
            "return nil;" +
            "end;" +
            "return redis.call('pttl',KEYS[1]);";

    /**
     * 分布式锁,key为lock + lockName,value为 "持有此锁的线程的名称 : 此微服务的名称"
     *
     * @param lockName
     * @param thread
     */
    public void lock(String lockName, Thread thread, Long outTime) {
        Jedis jedis = redisPool.getJedis();
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> args = new ArrayList<>();
        keys.add("lock_" + lockName);
        args.add();
        jedis.evalsha(REDIS_GET_LOCK_LUA, keys, args);
    }
}
