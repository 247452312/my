package indi.uhyils.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.pojo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

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

    public void addUser(String token, UserEntity user) {
        Jedis jedis = redisPool.getJedis();
        String value = JSONObject.toJSONString(user);
        jedis.append(token, value);
        //半个小时
        jedis.expire(token, 60 * 30);
        jedis.close();
    }

    public UserEntity getUser(String token) {
        Jedis jedis = redisPool.getJedis();
        String userJson = jedis.get(token);
        jedis.close();
        if (userJson == null || "".equals(userJson)) {
            return null;
        }
        return JSON.parseObject(userJson, UserEntity.class);
    }

    public Boolean haveToken(String token){
        Jedis jedis = redisPool.getJedis();
        return jedis.exists(token);
    }


    public RedisPool getRedisPool() {
        return redisPool;
    }

    public void setRedisPool(RedisPool redisPool) {
        this.redisPool = redisPool;
    }
}
