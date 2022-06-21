package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.pojo.entity.Verification;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.redis.Redisable;
import indi.uhyils.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时27分
 */
@Repository
public class VerificationRepositoryImpl implements VerificationRepository {


    @Autowired
    private RedisPoolHandle redis;


    @Override
    public void saveVerificationToCache(String key, String code, Integer time) {
        try (Redisable jedis = redis.getJedis()) {
            jedis.set(key, code);
            jedis.expire(key, time);
        }
    }

    @Override
    public Boolean verification(Verification verification) {
        try (Redisable jedis = redis.getJedis()) {
            String realCode = jedis.get(verification.key());
            return verification.code().equals(realCode);
        }
    }

    @Override
    public void cleanCache(Verification verification) {
        try (Redisable jedis = redis.getJedis()) {
            jedis.del(verification.key());
        }
    }
}
