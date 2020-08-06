package indi.uhyils.util.redis;

import redis.clients.jedis.*;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.params.*;
import redis.clients.jedis.util.Slowlog;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月06日 07时33分
 */
public class Temp extends Jedis {
    public Temp() {
        super();
    }

    public Temp(String host) {
        super(host);
    }

    public Temp(HostAndPort hp) {
        super(hp);
    }

    public Temp(String host, int port) {
        super(host, port);
    }

    public Temp(String host, int port, boolean ssl) {
        super(host, port, ssl);
    }

    public Temp(String host, int port, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(host, port, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public Temp(String host, int port, int timeout) {
        super(host, port, timeout);
    }

    public Temp(String host, int port, int timeout, boolean ssl) {
        super(host, port, timeout, ssl);
    }

    public Temp(String host, int port, int timeout, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(host, port, timeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public Temp(String host, int port, int connectionTimeout, int soTimeout) {
        super(host, port, connectionTimeout, soTimeout);
    }

    public Temp(String host, int port, int connectionTimeout, int soTimeout, boolean ssl) {
        super(host, port, connectionTimeout, soTimeout, ssl);
    }

    public Temp(String host, int port, int connectionTimeout, int soTimeout, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(host, port, connectionTimeout, soTimeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public Temp(JedisShardInfo shardInfo) {
        super(shardInfo);
    }

    public Temp(URI uri) {
        super(uri);
    }

    public Temp(URI uri, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public Temp(URI uri, int timeout) {
        super(uri, timeout);
    }

    public Temp(URI uri, int timeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public Temp(URI uri, int connectionTimeout, int soTimeout) {
        super(uri, connectionTimeout, soTimeout);
    }

    public Temp(URI uri, int connectionTimeout, int soTimeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, connectionTimeout, soTimeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    @Override
    public String ping(String message) {
        return super.ping(message);
    }

    @Override
    public String set(String key, String value) {
        return super.set(key, value);
    }

    @Override
    public String set(String key, String value, SetParams params) {
        return super.set(key, value, params);
    }

    @Override
    public String get(String key) {
        return super.get(key);
    }

    @Override
    public Long exists(String... keys) {
        return super.exists(keys);
    }

    @Override
    public Boolean exists(String key) {
        return super.exists(key);
    }

    @Override
    public Long del(String... keys) {
        return super.del(keys);
    }

    @Override
    public Long del(String key) {
        return super.del(key);
    }

    @Override
    public Long unlink(String... keys) {
        return super.unlink(keys);
    }

    @Override
    public Long unlink(String key) {
        return super.unlink(key);
    }

    @Override
    public String type(String key) {
        return super.type(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        return super.keys(pattern);
    }

    @Override
    public String randomKey() {
        return super.randomKey();
    }

    @Override
    public String rename(String oldkey, String newkey) {
        return super.rename(oldkey, newkey);
    }

    @Override
    public Long renamenx(String oldkey, String newkey) {
        return super.renamenx(oldkey, newkey);
    }

    @Override
    public Long expire(String key, int seconds) {
        return super.expire(key, seconds);
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return super.expireAt(key, unixTime);
    }

    @Override
    public Long ttl(String key) {
        return super.ttl(key);
    }

    @Override
    public Long touch(String... keys) {
        return super.touch(keys);
    }

    @Override
    public Long touch(String key) {
        return super.touch(key);
    }

    @Override
    public Long move(String key, int dbIndex) {
        return super.move(key, dbIndex);
    }

    @Override
    public String getSet(String key, String value) {
        return super.getSet(key, value);
    }

    @Override
    public List<String> mget(String... keys) {
        return super.mget(keys);
    }

    @Override
    public Long setnx(String key, String value) {
        return super.setnx(key, value);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return super.setex(key, seconds, value);
    }

    @Override
    public String mset(String... keysvalues) {
        return super.mset(keysvalues);
    }

    @Override
    public Long msetnx(String... keysvalues) {
        return super.msetnx(keysvalues);
    }

    @Override
    public Long decrBy(String key, long decrement) {
        return super.decrBy(key, decrement);
    }

    @Override
    public Long decr(String key) {
        return super.decr(key);
    }

    @Override
    public Long incrBy(String key, long increment) {
        return super.incrBy(key, increment);
    }

    @Override
    public Double incrByFloat(String key, double increment) {
        return super.incrByFloat(key, increment);
    }

    @Override
    public Long incr(String key) {
        return super.incr(key);
    }

    @Override
    public Long append(String key, String value) {
        return super.append(key, value);
    }

    @Override
    public String substr(String key, int start, int end) {
        return super.substr(key, start, end);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return super.hset(key, field, value);
    }

    @Override
    public Long hset(String key, Map<String, String> hash) {
        return super.hset(key, hash);
    }

    @Override
    public String hget(String key, String field) {
        return super.hget(key, field);
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return super.hsetnx(key, field, value);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return super.hmset(key, hash);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return super.hmget(key, fields);
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        return super.hincrBy(key, field, value);
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        return super.hincrByFloat(key, field, value);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return super.hexists(key, field);
    }

    @Override
    public Long hdel(String key, String... fields) {
        return super.hdel(key, fields);
    }

    @Override
    public Long hlen(String key) {
        return super.hlen(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return super.hkeys(key);
    }

    @Override
    public List<String> hvals(String key) {
        return super.hvals(key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return super.hgetAll(key);
    }

    @Override
    public Long rpush(String key, String... strings) {
        return super.rpush(key, strings);
    }

    @Override
    public Long lpush(String key, String... strings) {
        return super.lpush(key, strings);
    }

    @Override
    public Long llen(String key) {
        return super.llen(key);
    }

    @Override
    public List<String> lrange(String key, long start, long stop) {
        return super.lrange(key, start, stop);
    }

    @Override
    public String ltrim(String key, long start, long stop) {
        return super.ltrim(key, start, stop);
    }

    @Override
    public String lindex(String key, long index) {
        return super.lindex(key, index);
    }

    @Override
    public String lset(String key, long index, String value) {
        return super.lset(key, index, value);
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return super.lrem(key, count, value);
    }

    @Override
    public String lpop(String key) {
        return super.lpop(key);
    }

    @Override
    public String rpop(String key) {
        return super.rpop(key);
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        return super.rpoplpush(srckey, dstkey);
    }

    @Override
    public Long sadd(String key, String... members) {
        return super.sadd(key, members);
    }

    @Override
    public Set<String> smembers(String key) {
        return super.smembers(key);
    }

    @Override
    public Long srem(String key, String... members) {
        return super.srem(key, members);
    }

    @Override
    public String spop(String key) {
        return super.spop(key);
    }

    @Override
    public Set<String> spop(String key, long count) {
        return super.spop(key, count);
    }

    @Override
    public Long smove(String srckey, String dstkey, String member) {
        return super.smove(srckey, dstkey, member);
    }

    @Override
    public Long scard(String key) {
        return super.scard(key);
    }

    @Override
    public Boolean sismember(String key, String member) {
        return super.sismember(key, member);
    }

    @Override
    public Set<String> sinter(String... keys) {
        return super.sinter(keys);
    }

    @Override
    public Long sinterstore(String dstkey, String... keys) {
        return super.sinterstore(dstkey, keys);
    }

    @Override
    public Set<String> sunion(String... keys) {
        return super.sunion(keys);
    }

    @Override
    public Long sunionstore(String dstkey, String... keys) {
        return super.sunionstore(dstkey, keys);
    }

    @Override
    public Set<String> sdiff(String... keys) {
        return super.sdiff(keys);
    }

    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        return super.sdiffstore(dstkey, keys);
    }

    @Override
    public String srandmember(String key) {
        return super.srandmember(key);
    }

    @Override
    public List<String> srandmember(String key, int count) {
        return super.srandmember(key, count);
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return super.zadd(key, score, member);
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams params) {
        return super.zadd(key, score, member, params);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return super.zadd(key, scoreMembers);
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        return super.zadd(key, scoreMembers, params);
    }

    @Override
    public Set<String> zrange(String key, long start, long stop) {
        return super.zrange(key, start, stop);
    }

    @Override
    public Long zrem(String key, String... members) {
        return super.zrem(key, members);
    }

    @Override
    public Double zincrby(String key, double increment, String member) {
        return super.zincrby(key, increment, member);
    }

    @Override
    public Double zincrby(String key, double increment, String member, ZIncrByParams params) {
        return super.zincrby(key, increment, member, params);
    }

    @Override
    public Long zrank(String key, String member) {
        return super.zrank(key, member);
    }

    @Override
    public Long zrevrank(String key, String member) {
        return super.zrevrank(key, member);
    }

    @Override
    public Set<String> zrevrange(String key, long start, long stop) {
        return super.zrevrange(key, start, stop);
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long stop) {
        return super.zrangeWithScores(key, start, stop);
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long stop) {
        return super.zrevrangeWithScores(key, start, stop);
    }

    @Override
    public Long zcard(String key) {
        return super.zcard(key);
    }

    @Override
    public Double zscore(String key, String member) {
        return super.zscore(key, member);
    }

    @Override
    public Tuple zpopmax(String key) {
        return super.zpopmax(key);
    }

    @Override
    public Set<Tuple> zpopmax(String key, int count) {
        return super.zpopmax(key, count);
    }

    @Override
    public Tuple zpopmin(String key) {
        return super.zpopmin(key);
    }

    @Override
    public Set<Tuple> zpopmin(String key, int count) {
        return super.zpopmin(key, count);
    }

    @Override
    public String watch(String... keys) {
        return super.watch(keys);
    }

    @Override
    public List<String> sort(String key) {
        return super.sort(key);
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        return super.sort(key, sortingParameters);
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        return super.blpop(timeout, keys);
    }

    @Override
    public List<String> blpop(String... args) {
        return super.blpop(args);
    }

    @Override
    public List<String> brpop(String... args) {
        return super.brpop(args);
    }

    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        return super.sort(key, sortingParameters, dstkey);
    }

    @Override
    public Long sort(String key, String dstkey) {
        return super.sort(key, dstkey);
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        return super.brpop(timeout, keys);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return super.zcount(key, min, max);
    }

    @Override
    public Long zcount(String key, String min, String max) {
        return super.zcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return super.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        return super.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return super.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return super.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return super.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return super.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return super.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return super.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return super.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return super.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return super.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return super.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return super.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return super.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return super.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return super.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Long zremrangeByRank(String key, long start, long stop) {
        return super.zremrangeByRank(key, start, stop);
    }

    @Override
    public Long zremrangeByScore(String key, double min, double max) {
        return super.zremrangeByScore(key, min, max);
    }

    @Override
    public Long zremrangeByScore(String key, String min, String max) {
        return super.zremrangeByScore(key, min, max);
    }

    @Override
    public Long zunionstore(String dstkey, String... sets) {
        return super.zunionstore(dstkey, sets);
    }

    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        return super.zunionstore(dstkey, params, sets);
    }

    @Override
    public Long zinterstore(String dstkey, String... sets) {
        return super.zinterstore(dstkey, sets);
    }

    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        return super.zinterstore(dstkey, params, sets);
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        return super.zlexcount(key, min, max);
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        return super.zrangeByLex(key, min, max);
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return super.zrangeByLex(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        return super.zrevrangeByLex(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return super.zrevrangeByLex(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        return super.zremrangeByLex(key, min, max);
    }

    @Override
    public Long strlen(String key) {
        return super.strlen(key);
    }

    @Override
    public Long lpushx(String key, String... string) {
        return super.lpushx(key, string);
    }

    @Override
    public Long persist(String key) {
        return super.persist(key);
    }

    @Override
    public Long rpushx(String key, String... string) {
        return super.rpushx(key, string);
    }

    @Override
    public String echo(String string) {
        return super.echo(string);
    }

    @Override
    public Long linsert(String key, ListPosition where, String pivot, String value) {
        return super.linsert(key, where, pivot, value);
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        return super.brpoplpush(source, destination, timeout);
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        return super.setbit(key, offset, value);
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        return super.setbit(key, offset, value);
    }

    @Override
    public Boolean getbit(String key, long offset) {
        return super.getbit(key, offset);
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return super.setrange(key, offset, value);
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return super.getrange(key, startOffset, endOffset);
    }

    @Override
    public Long bitpos(String key, boolean value) {
        return super.bitpos(key, value);
    }

    @Override
    public Long bitpos(String key, boolean value, BitPosParams params) {
        return super.bitpos(key, value, params);
    }

    @Override
    public List<String> configGet(String pattern) {
        return super.configGet(pattern);
    }

    @Override
    public String configSet(String parameter, String value) {
        return super.configSet(parameter, value);
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        super.subscribe(jedisPubSub, channels);
    }

    @Override
    public Long publish(String channel, String message) {
        return super.publish(channel, message);
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        super.psubscribe(jedisPubSub, patterns);
    }

    @Override
    public Object eval(String script, int keyCount, String... params) {
        return super.eval(script, keyCount, params);
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        return super.eval(script, keys, args);
    }

    @Override
    public Object eval(String script) {
        return super.eval(script);
    }

    @Override
    public Object evalsha(String sha1) {
        return super.evalsha(sha1);
    }

    @Override
    public Object evalsha(String sha1, List<String> keys, List<String> args) {
        return super.evalsha(sha1, keys, args);
    }

    @Override
    public Object evalsha(String sha1, int keyCount, String... params) {
        return super.evalsha(sha1, keyCount, params);
    }

    @Override
    public Boolean scriptExists(String sha1) {
        return super.scriptExists(sha1);
    }

    @Override
    public List<Boolean> scriptExists(String... sha1) {
        return super.scriptExists(sha1);
    }

    @Override
    public String scriptLoad(String script) {
        return super.scriptLoad(script);
    }

    @Override
    public List<Slowlog> slowlogGet() {
        return super.slowlogGet();
    }

    @Override
    public List<Slowlog> slowlogGet(long entries) {
        return super.slowlogGet(entries);
    }

    @Override
    public Long objectRefcount(String key) {
        return super.objectRefcount(key);
    }

    @Override
    public String objectEncoding(String key) {
        return super.objectEncoding(key);
    }

    @Override
    public Long objectIdletime(String key) {
        return super.objectIdletime(key);
    }

    @Override
    public Long bitcount(String key) {
        return super.bitcount(key);
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        return super.bitcount(key, start, end);
    }

    @Override
    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        return super.bitop(op, destKey, srcKeys);
    }

    @Override
    public List<Map<String, String>> sentinelMasters() {
        return super.sentinelMasters();
    }

    @Override
    public List<String> sentinelGetMasterAddrByName(String masterName) {
        return super.sentinelGetMasterAddrByName(masterName);
    }

    @Override
    public Long sentinelReset(String pattern) {
        return super.sentinelReset(pattern);
    }

    @Override
    public List<Map<String, String>> sentinelSlaves(String masterName) {
        return super.sentinelSlaves(masterName);
    }

    @Override
    public String sentinelFailover(String masterName) {
        return super.sentinelFailover(masterName);
    }

    @Override
    public String sentinelMonitor(String masterName, String ip, int port, int quorum) {
        return super.sentinelMonitor(masterName, ip, port, quorum);
    }

    @Override
    public String sentinelRemove(String masterName) {
        return super.sentinelRemove(masterName);
    }

    @Override
    public String sentinelSet(String masterName, Map<String, String> parameterMap) {
        return super.sentinelSet(masterName, parameterMap);
    }

    @Override
    public byte[] dump(String key) {
        return super.dump(key);
    }

    @Override
    public String restore(String key, int ttl, byte[] serializedValue) {
        return super.restore(key, ttl, serializedValue);
    }

    @Override
    public String restoreReplace(String key, int ttl, byte[] serializedValue) {
        return super.restoreReplace(key, ttl, serializedValue);
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        return super.pexpire(key, milliseconds);
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return super.pexpireAt(key, millisecondsTimestamp);
    }

    @Override
    public Long pttl(String key) {
        return super.pttl(key);
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        return super.psetex(key, milliseconds, value);
    }

    @Override
    public String clientKill(String ipPort) {
        return super.clientKill(ipPort);
    }

    @Override
    public String clientGetname() {
        return super.clientGetname();
    }

    @Override
    public String clientList() {
        return super.clientList();
    }

    @Override
    public String clientSetname(String name) {
        return super.clientSetname(name);
    }

    @Override
    public String migrate(String host, int port, String key, int destinationDb, int timeout) {
        return super.migrate(host, port, key, destinationDb, timeout);
    }

    @Override
    public String migrate(String host, int port, int destinationDB, int timeout, MigrateParams params, String... keys) {
        return super.migrate(host, port, destinationDB, timeout, params, keys);
    }

    @Override
    public ScanResult<String> scan(String cursor) {
        return super.scan(cursor);
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        return super.scan(cursor, params);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return super.hscan(key, cursor);
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return super.hscan(key, cursor, params);
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        return super.sscan(key, cursor);
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        return super.sscan(key, cursor, params);
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        return super.zscan(key, cursor);
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return super.zscan(key, cursor, params);
    }

    @Override
    public String clusterNodes() {
        return super.clusterNodes();
    }

    @Override
    public String readonly() {
        return super.readonly();
    }

    @Override
    public String clusterMeet(String ip, int port) {
        return super.clusterMeet(ip, port);
    }

    @Override
    public String clusterReset(ClusterReset resetType) {
        return super.clusterReset(resetType);
    }

    @Override
    public String clusterAddSlots(int... slots) {
        return super.clusterAddSlots(slots);
    }

    @Override
    public String clusterDelSlots(int... slots) {
        return super.clusterDelSlots(slots);
    }

    @Override
    public String clusterInfo() {
        return super.clusterInfo();
    }

    @Override
    public List<String> clusterGetKeysInSlot(int slot, int count) {
        return super.clusterGetKeysInSlot(slot, count);
    }

    @Override
    public String clusterSetSlotNode(int slot, String nodeId) {
        return super.clusterSetSlotNode(slot, nodeId);
    }

    @Override
    public String clusterSetSlotMigrating(int slot, String nodeId) {
        return super.clusterSetSlotMigrating(slot, nodeId);
    }

    @Override
    public String clusterSetSlotImporting(int slot, String nodeId) {
        return super.clusterSetSlotImporting(slot, nodeId);
    }

    @Override
    public String clusterSetSlotStable(int slot) {
        return super.clusterSetSlotStable(slot);
    }

    @Override
    public String clusterForget(String nodeId) {
        return super.clusterForget(nodeId);
    }

    @Override
    public String clusterFlushSlots() {
        return super.clusterFlushSlots();
    }

    @Override
    public Long clusterKeySlot(String key) {
        return super.clusterKeySlot(key);
    }

    @Override
    public Long clusterCountKeysInSlot(int slot) {
        return super.clusterCountKeysInSlot(slot);
    }

    @Override
    public String clusterSaveConfig() {
        return super.clusterSaveConfig();
    }

    @Override
    public String clusterReplicate(String nodeId) {
        return super.clusterReplicate(nodeId);
    }

    @Override
    public List<String> clusterSlaves(String nodeId) {
        return super.clusterSlaves(nodeId);
    }

    @Override
    public String clusterFailover() {
        return super.clusterFailover();
    }

    @Override
    public List<Object> clusterSlots() {
        return super.clusterSlots();
    }

    @Override
    public String asking() {
        return super.asking();
    }

    @Override
    public List<String> pubsubChannels(String pattern) {
        return super.pubsubChannels(pattern);
    }

    @Override
    public Long pubsubNumPat() {
        return super.pubsubNumPat();
    }

    @Override
    public Map<String, String> pubsubNumSub(String... channels) {
        return super.pubsubNumSub(channels);
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void setDataSource(JedisPoolAbstract jedisPool) {
        super.setDataSource(jedisPool);
    }

    @Override
    public Long pfadd(String key, String... elements) {
        return super.pfadd(key, elements);
    }

    @Override
    public long pfcount(String key) {
        return super.pfcount(key);
    }

    @Override
    public long pfcount(String... keys) {
        return super.pfcount(keys);
    }

    @Override
    public String pfmerge(String destkey, String... sourcekeys) {
        return super.pfmerge(destkey, sourcekeys);
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        return super.blpop(timeout, key);
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        return super.brpop(timeout, key);
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        return super.geoadd(key, longitude, latitude, member);
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return super.geoadd(key, memberCoordinateMap);
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        return super.geodist(key, member1, member2);
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return super.geodist(key, member1, member2, unit);
    }

    @Override
    public List<String> geohash(String key, String... members) {
        return super.geohash(key, members);
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        return super.geopos(key, members);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return super.georadius(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return super.georadiusReadonly(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return super.georadius(key, longitude, latitude, radius, unit, param);
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return super.georadiusReadonly(key, longitude, latitude, radius, unit, param);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return super.georadiusByMember(key, member, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit) {
        return super.georadiusByMemberReadonly(key, member, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return super.georadiusByMember(key, member, radius, unit, param);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return super.georadiusByMemberReadonly(key, member, radius, unit, param);
    }

    @Override
    public String moduleLoad(String path) {
        return super.moduleLoad(path);
    }

    @Override
    public String moduleUnload(String name) {
        return super.moduleUnload(name);
    }

    @Override
    public List<Module> moduleList() {
        return super.moduleList();
    }

    @Override
    public List<Long> bitfield(String key, String... arguments) {
        return super.bitfield(key, arguments);
    }

    @Override
    public Long hstrlen(String key, String field) {
        return super.hstrlen(key, field);
    }

    @Override
    public String memoryDoctor() {
        return super.memoryDoctor();
    }

    @Override
    public StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash) {
        return super.xadd(key, id, hash);
    }

    @Override
    public StreamEntryID xadd(String key, StreamEntryID id, Map<String, String> hash, long maxLen, boolean approximateLength) {
        return super.xadd(key, id, hash, maxLen, approximateLength);
    }

    @Override
    public Long xlen(String key) {
        return super.xlen(key);
    }

    @Override
    public List<StreamEntry> xrange(String key, StreamEntryID start, StreamEntryID end, int count) {
        return super.xrange(key, start, end, count);
    }

    @Override
    public List<StreamEntry> xrevrange(String key, StreamEntryID end, StreamEntryID start, int count) {
        return super.xrevrange(key, end, start, count);
    }

    @Override
    public List<Map.Entry<String, List<StreamEntry>>> xread(int count, long block, Map.Entry<String, StreamEntryID>... streams) {
        return super.xread(count, block, streams);
    }

    @Override
    public long xack(String key, String group, StreamEntryID... ids) {
        return super.xack(key, group, ids);
    }

    @Override
    public String xgroupCreate(String key, String groupname, StreamEntryID id, boolean makeStream) {
        return super.xgroupCreate(key, groupname, id, makeStream);
    }

    @Override
    public String xgroupSetID(String key, String groupname, StreamEntryID id) {
        return super.xgroupSetID(key, groupname, id);
    }

    @Override
    public long xgroupDestroy(String key, String groupname) {
        return super.xgroupDestroy(key, groupname);
    }

    @Override
    public String xgroupDelConsumer(String key, String groupname, String consumerName) {
        return super.xgroupDelConsumer(key, groupname, consumerName);
    }

    @Override
    public long xdel(String key, StreamEntryID... ids) {
        return super.xdel(key, ids);
    }

    @Override
    public long xtrim(String key, long maxLen, boolean approximateLength) {
        return super.xtrim(key, maxLen, approximateLength);
    }

    @Override
    public List<Map.Entry<String, List<StreamEntry>>> xreadGroup(String groupname, String consumer, int count, long block, boolean noAck, Map.Entry<String, StreamEntryID>... streams) {
        return super.xreadGroup(groupname, consumer, count, block, noAck, streams);
    }

    @Override
    public List<StreamPendingEntry> xpending(String key, String groupname, StreamEntryID start, StreamEntryID end, int count, String consumername) {
        return super.xpending(key, groupname, start, end, count, consumername);
    }

    @Override
    public List<StreamEntry> xclaim(String key, String group, String consumername, long minIdleTime, long newIdleTime, int retries, boolean force, StreamEntryID... ids) {
        return super.xclaim(key, group, consumername, minIdleTime, newIdleTime, retries, force, ids);
    }

    @Override
    public Object sendCommand(ProtocolCommand cmd, String... args) {
        return super.sendCommand(cmd, args);
    }

    @Override
    public String ping() {
        return super.ping();
    }

    @Override
    public byte[] ping(byte[] message) {
        return super.ping(message);
    }

    @Override
    public String set(byte[] key, byte[] value) {
        return super.set(key, value);
    }

    @Override
    public String set(byte[] key, byte[] value, SetParams params) {
        return super.set(key, value, params);
    }

    @Override
    public byte[] get(byte[] key) {
        return super.get(key);
    }

    @Override
    public String quit() {
        return super.quit();
    }

    @Override
    public Long exists(byte[]... keys) {
        return super.exists(keys);
    }

    @Override
    public Boolean exists(byte[] key) {
        return super.exists(key);
    }

    @Override
    public Long del(byte[]... keys) {
        return super.del(keys);
    }

    @Override
    public Long del(byte[] key) {
        return super.del(key);
    }

    @Override
    public Long unlink(byte[]... keys) {
        return super.unlink(keys);
    }

    @Override
    public Long unlink(byte[] key) {
        return super.unlink(key);
    }

    @Override
    public String type(byte[] key) {
        return super.type(key);
    }

    @Override
    public String flushDB() {
        return super.flushDB();
    }

    @Override
    public Set<byte[]> keys(byte[] pattern) {
        return super.keys(pattern);
    }

    @Override
    public byte[] randomBinaryKey() {
        return super.randomBinaryKey();
    }

    @Override
    public String rename(byte[] oldkey, byte[] newkey) {
        return super.rename(oldkey, newkey);
    }

    @Override
    public Long renamenx(byte[] oldkey, byte[] newkey) {
        return super.renamenx(oldkey, newkey);
    }

    @Override
    public Long dbSize() {
        return super.dbSize();
    }

    @Override
    public Long expire(byte[] key, int seconds) {
        return super.expire(key, seconds);
    }

    @Override
    public Long expireAt(byte[] key, long unixTime) {
        return super.expireAt(key, unixTime);
    }

    @Override
    public Long ttl(byte[] key) {
        return super.ttl(key);
    }

    @Override
    public Long touch(byte[]... keys) {
        return super.touch(keys);
    }

    @Override
    public Long touch(byte[] key) {
        return super.touch(key);
    }

    @Override
    public String select(int index) {
        return super.select(index);
    }

    @Override
    public String swapDB(int index1, int index2) {
        return super.swapDB(index1, index2);
    }

    @Override
    public Long move(byte[] key, int dbIndex) {
        return super.move(key, dbIndex);
    }

    @Override
    public String flushAll() {
        return super.flushAll();
    }

    @Override
    public byte[] getSet(byte[] key, byte[] value) {
        return super.getSet(key, value);
    }

    @Override
    public List<byte[]> mget(byte[]... keys) {
        return super.mget(keys);
    }

    @Override
    public Long setnx(byte[] key, byte[] value) {
        return super.setnx(key, value);
    }

    @Override
    public String setex(byte[] key, int seconds, byte[] value) {
        return super.setex(key, seconds, value);
    }

    @Override
    public String mset(byte[]... keysvalues) {
        return super.mset(keysvalues);
    }

    @Override
    public Long msetnx(byte[]... keysvalues) {
        return super.msetnx(keysvalues);
    }

    @Override
    public Long decrBy(byte[] key, long decrement) {
        return super.decrBy(key, decrement);
    }

    @Override
    public Long decr(byte[] key) {
        return super.decr(key);
    }

    @Override
    public Long incrBy(byte[] key, long increment) {
        return super.incrBy(key, increment);
    }

    @Override
    public Double incrByFloat(byte[] key, double increment) {
        return super.incrByFloat(key, increment);
    }

    @Override
    public Long incr(byte[] key) {
        return super.incr(key);
    }

    @Override
    public Long append(byte[] key, byte[] value) {
        return super.append(key, value);
    }

    @Override
    public byte[] substr(byte[] key, int start, int end) {
        return super.substr(key, start, end);
    }

    @Override
    public Long hset(byte[] key, byte[] field, byte[] value) {
        return super.hset(key, field, value);
    }

    @Override
    public Long hset(byte[] key, Map<byte[], byte[]> hash) {
        return super.hset(key, hash);
    }

    @Override
    public byte[] hget(byte[] key, byte[] field) {
        return super.hget(key, field);
    }

    @Override
    public Long hsetnx(byte[] key, byte[] field, byte[] value) {
        return super.hsetnx(key, field, value);
    }

    @Override
    public String hmset(byte[] key, Map<byte[], byte[]> hash) {
        return super.hmset(key, hash);
    }

    @Override
    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        return super.hmget(key, fields);
    }

    @Override
    public Long hincrBy(byte[] key, byte[] field, long value) {
        return super.hincrBy(key, field, value);
    }

    @Override
    public Double hincrByFloat(byte[] key, byte[] field, double value) {
        return super.hincrByFloat(key, field, value);
    }

    @Override
    public Boolean hexists(byte[] key, byte[] field) {
        return super.hexists(key, field);
    }

    @Override
    public Long hdel(byte[] key, byte[]... fields) {
        return super.hdel(key, fields);
    }

    @Override
    public Long hlen(byte[] key) {
        return super.hlen(key);
    }

    @Override
    public Set<byte[]> hkeys(byte[] key) {
        return super.hkeys(key);
    }

    @Override
    public List<byte[]> hvals(byte[] key) {
        return super.hvals(key);
    }

    @Override
    public Map<byte[], byte[]> hgetAll(byte[] key) {
        return super.hgetAll(key);
    }

    @Override
    public Long rpush(byte[] key, byte[]... strings) {
        return super.rpush(key, strings);
    }

    @Override
    public Long lpush(byte[] key, byte[]... strings) {
        return super.lpush(key, strings);
    }

    @Override
    public Long llen(byte[] key) {
        return super.llen(key);
    }

    @Override
    public List<byte[]> lrange(byte[] key, long start, long stop) {
        return super.lrange(key, start, stop);
    }

    @Override
    public String ltrim(byte[] key, long start, long stop) {
        return super.ltrim(key, start, stop);
    }

    @Override
    public byte[] lindex(byte[] key, long index) {
        return super.lindex(key, index);
    }

    @Override
    public String lset(byte[] key, long index, byte[] value) {
        return super.lset(key, index, value);
    }

    @Override
    public Long lrem(byte[] key, long count, byte[] value) {
        return super.lrem(key, count, value);
    }

    @Override
    public byte[] lpop(byte[] key) {
        return super.lpop(key);
    }

    @Override
    public byte[] rpop(byte[] key) {
        return super.rpop(key);
    }

    @Override
    public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
        return super.rpoplpush(srckey, dstkey);
    }

    @Override
    public Long sadd(byte[] key, byte[]... members) {
        return super.sadd(key, members);
    }

    @Override
    public Set<byte[]> smembers(byte[] key) {
        return super.smembers(key);
    }

    @Override
    public Long srem(byte[] key, byte[]... member) {
        return super.srem(key, member);
    }

    @Override
    public byte[] spop(byte[] key) {
        return super.spop(key);
    }

    @Override
    public Set<byte[]> spop(byte[] key, long count) {
        return super.spop(key, count);
    }

    @Override
    public Long smove(byte[] srckey, byte[] dstkey, byte[] member) {
        return super.smove(srckey, dstkey, member);
    }

    @Override
    public Long scard(byte[] key) {
        return super.scard(key);
    }

    @Override
    public Boolean sismember(byte[] key, byte[] member) {
        return super.sismember(key, member);
    }

    @Override
    public Set<byte[]> sinter(byte[]... keys) {
        return super.sinter(keys);
    }

    @Override
    public Long sinterstore(byte[] dstkey, byte[]... keys) {
        return super.sinterstore(dstkey, keys);
    }

    @Override
    public Set<byte[]> sunion(byte[]... keys) {
        return super.sunion(keys);
    }

    @Override
    public Long sunionstore(byte[] dstkey, byte[]... keys) {
        return super.sunionstore(dstkey, keys);
    }

    @Override
    public Set<byte[]> sdiff(byte[]... keys) {
        return super.sdiff(keys);
    }

    @Override
    public Long sdiffstore(byte[] dstkey, byte[]... keys) {
        return super.sdiffstore(dstkey, keys);
    }

    @Override
    public byte[] srandmember(byte[] key) {
        return super.srandmember(key);
    }

    @Override
    public List<byte[]> srandmember(byte[] key, int count) {
        return super.srandmember(key, count);
    }

    @Override
    public Long zadd(byte[] key, double score, byte[] member) {
        return super.zadd(key, score, member);
    }

    @Override
    public Long zadd(byte[] key, double score, byte[] member, ZAddParams params) {
        return super.zadd(key, score, member, params);
    }

    @Override
    public Long zadd(byte[] key, Map<byte[], Double> scoreMembers) {
        return super.zadd(key, scoreMembers);
    }

    @Override
    public Long zadd(byte[] key, Map<byte[], Double> scoreMembers, ZAddParams params) {
        return super.zadd(key, scoreMembers, params);
    }

    @Override
    public Set<byte[]> zrange(byte[] key, long start, long stop) {
        return super.zrange(key, start, stop);
    }

    @Override
    public Long zrem(byte[] key, byte[]... members) {
        return super.zrem(key, members);
    }

    @Override
    public Double zincrby(byte[] key, double increment, byte[] member) {
        return super.zincrby(key, increment, member);
    }

    @Override
    public Double zincrby(byte[] key, double increment, byte[] member, ZIncrByParams params) {
        return super.zincrby(key, increment, member, params);
    }

    @Override
    public Long zrank(byte[] key, byte[] member) {
        return super.zrank(key, member);
    }

    @Override
    public Long zrevrank(byte[] key, byte[] member) {
        return super.zrevrank(key, member);
    }

    @Override
    public Set<byte[]> zrevrange(byte[] key, long start, long stop) {
        return super.zrevrange(key, start, stop);
    }

    @Override
    public Set<Tuple> zrangeWithScores(byte[] key, long start, long stop) {
        return super.zrangeWithScores(key, start, stop);
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(byte[] key, long start, long stop) {
        return super.zrevrangeWithScores(key, start, stop);
    }

    @Override
    public Long zcard(byte[] key) {
        return super.zcard(key);
    }

    @Override
    public Double zscore(byte[] key, byte[] member) {
        return super.zscore(key, member);
    }

    @Override
    public Tuple zpopmax(byte[] key) {
        return super.zpopmax(key);
    }

    @Override
    public Set<Tuple> zpopmax(byte[] key, int count) {
        return super.zpopmax(key, count);
    }

    @Override
    public Tuple zpopmin(byte[] key) {
        return super.zpopmin(key);
    }

    @Override
    public Set<Tuple> zpopmin(byte[] key, int count) {
        return super.zpopmin(key, count);
    }

    @Override
    public Transaction multi() {
        return super.multi();
    }

    @Override
    protected void checkIsInMultiOrPipeline() {
        super.checkIsInMultiOrPipeline();
    }

    @Override
    public void connect() {
        super.connect();
    }

    @Override
    public void disconnect() {
        super.disconnect();
    }

    @Override
    public void resetState() {
        super.resetState();
    }

    @Override
    public String watch(byte[]... keys) {
        return super.watch(keys);
    }

    @Override
    public String unwatch() {
        return super.unwatch();
    }

    @Override
    public List<byte[]> sort(byte[] key) {
        return super.sort(key);
    }

    @Override
    public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
        return super.sort(key, sortingParameters);
    }

    @Override
    public List<byte[]> blpop(int timeout, byte[]... keys) {
        return super.blpop(timeout, keys);
    }

    @Override
    public Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey) {
        return super.sort(key, sortingParameters, dstkey);
    }

    @Override
    public Long sort(byte[] key, byte[] dstkey) {
        return super.sort(key, dstkey);
    }

    @Override
    public List<byte[]> brpop(int timeout, byte[]... keys) {
        return super.brpop(timeout, keys);
    }

    @Override
    public List<byte[]> blpop(byte[]... args) {
        return super.blpop(args);
    }

    @Override
    public List<byte[]> brpop(byte[]... args) {
        return super.brpop(args);
    }

    @Override
    public String auth(String password) {
        return super.auth(password);
    }

    @Override
    public Pipeline pipelined() {
        return super.pipelined();
    }

    @Override
    public Long zcount(byte[] key, double min, double max) {
        return super.zcount(key, min, max);
    }

    @Override
    public Long zcount(byte[] key, byte[] min, byte[] max) {
        return super.zcount(key, min, max);
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
        return super.zrangeByScore(key, min, max);
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max) {
        return super.zrangeByScore(key, min, max);
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
        return super.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return super.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        return super.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {
        return super.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        return super.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return super.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    protected Set<Tuple> getTupledSet() {
        return super.getTupledSet();
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        return super.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
        return super.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        return super.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return super.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        return super.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        return super.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min) {
        return super.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return super.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByRank(byte[] key, long start, long stop) {
        return super.zremrangeByRank(key, start, stop);
    }

    @Override
    public Long zremrangeByScore(byte[] key, double min, double max) {
        return super.zremrangeByScore(key, min, max);
    }

    @Override
    public Long zremrangeByScore(byte[] key, byte[] min, byte[] max) {
        return super.zremrangeByScore(key, min, max);
    }

    @Override
    public Long zunionstore(byte[] dstkey, byte[]... sets) {
        return super.zunionstore(dstkey, sets);
    }

    @Override
    public Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets) {
        return super.zunionstore(dstkey, params, sets);
    }

    @Override
    public Long zinterstore(byte[] dstkey, byte[]... sets) {
        return super.zinterstore(dstkey, sets);
    }

    @Override
    public Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets) {
        return super.zinterstore(dstkey, params, sets);
    }

    @Override
    public Long zlexcount(byte[] key, byte[] min, byte[] max) {
        return super.zlexcount(key, min, max);
    }

    @Override
    public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max) {
        return super.zrangeByLex(key, min, max);
    }

    @Override
    public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return super.zrangeByLex(key, min, max, offset, count);
    }

    @Override
    public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min) {
        return super.zrevrangeByLex(key, max, min);
    }

    @Override
    public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return super.zrevrangeByLex(key, max, min, offset, count);
    }

    @Override
    public Long zremrangeByLex(byte[] key, byte[] min, byte[] max) {
        return super.zremrangeByLex(key, min, max);
    }

    @Override
    public String save() {
        return super.save();
    }

    @Override
    public String bgsave() {
        return super.bgsave();
    }

    @Override
    public String bgrewriteaof() {
        return super.bgrewriteaof();
    }

    @Override
    public Long lastsave() {
        return super.lastsave();
    }

    @Override
    public String shutdown() {
        return super.shutdown();
    }

    @Override
    public String info() {
        return super.info();
    }

    @Override
    public String info(String section) {
        return super.info(section);
    }

    @Override
    public void monitor(JedisMonitor jedisMonitor) {
        super.monitor(jedisMonitor);
    }

    @Override
    public String slaveof(String host, int port) {
        return super.slaveof(host, port);
    }

    @Override
    public String slaveofNoOne() {
        return super.slaveofNoOne();
    }

    @Override
    public List<byte[]> configGet(byte[] pattern) {
        return super.configGet(pattern);
    }

    @Override
    public String configResetStat() {
        return super.configResetStat();
    }

    @Override
    public String configRewrite() {
        return super.configRewrite();
    }

    @Override
    public byte[] configSet(byte[] parameter, byte[] value) {
        return super.configSet(parameter, value);
    }

    @Override
    public boolean isConnected() {
        return super.isConnected();
    }

    @Override
    public Long strlen(byte[] key) {
        return super.strlen(key);
    }

    @Override
    public void sync() {
        super.sync();
    }

    @Override
    public Long lpushx(byte[] key, byte[]... string) {
        return super.lpushx(key, string);
    }

    @Override
    public Long persist(byte[] key) {
        return super.persist(key);
    }

    @Override
    public Long rpushx(byte[] key, byte[]... string) {
        return super.rpushx(key, string);
    }

    @Override
    public byte[] echo(byte[] string) {
        return super.echo(string);
    }

    @Override
    public Long linsert(byte[] key, ListPosition where, byte[] pivot, byte[] value) {
        return super.linsert(key, where, pivot, value);
    }

    @Override
    public String debug(DebugParams params) {
        return super.debug(params);
    }

    @Override
    public Client getClient() {
        return super.getClient();
    }

    @Override
    public byte[] brpoplpush(byte[] source, byte[] destination, int timeout) {
        return super.brpoplpush(source, destination, timeout);
    }

    @Override
    public Boolean setbit(byte[] key, long offset, boolean value) {
        return super.setbit(key, offset, value);
    }

    @Override
    public Boolean setbit(byte[] key, long offset, byte[] value) {
        return super.setbit(key, offset, value);
    }

    @Override
    public Boolean getbit(byte[] key, long offset) {
        return super.getbit(key, offset);
    }

    @Override
    public Long bitpos(byte[] key, boolean value) {
        return super.bitpos(key, value);
    }

    @Override
    public Long bitpos(byte[] key, boolean value, BitPosParams params) {
        return super.bitpos(key, value, params);
    }

    @Override
    public Long setrange(byte[] key, long offset, byte[] value) {
        return super.setrange(key, offset, value);
    }

    @Override
    public byte[] getrange(byte[] key, long startOffset, long endOffset) {
        return super.getrange(key, startOffset, endOffset);
    }

    @Override
    public Long publish(byte[] channel, byte[] message) {
        return super.publish(channel, message);
    }

    @Override
    public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {
        super.subscribe(jedisPubSub, channels);
    }

    @Override
    public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {
        super.psubscribe(jedisPubSub, patterns);
    }

    @Override
    public int getDB() {
        return super.getDB();
    }

    @Override
    public Object eval(byte[] script, List<byte[]> keys, List<byte[]> args) {
        return super.eval(script, keys, args);
    }

    @Override
    public Object eval(byte[] script, byte[] keyCount, byte[]... params) {
        return super.eval(script, keyCount, params);
    }

    @Override
    public Object eval(byte[] script, int keyCount, byte[]... params) {
        return super.eval(script, keyCount, params);
    }

    @Override
    public Object eval(byte[] script) {
        return super.eval(script);
    }

    @Override
    public Object evalsha(byte[] sha1) {
        return super.evalsha(sha1);
    }

    @Override
    public Object evalsha(byte[] sha1, List<byte[]> keys, List<byte[]> args) {
        return super.evalsha(sha1, keys, args);
    }

    @Override
    public Object evalsha(byte[] sha1, int keyCount, byte[]... params) {
        return super.evalsha(sha1, keyCount, params);
    }

    @Override
    public String scriptFlush() {
        return super.scriptFlush();
    }

    @Override
    public Long scriptExists(byte[] sha1) {
        return super.scriptExists(sha1);
    }

    @Override
    public List<Long> scriptExists(byte[]... sha1) {
        return super.scriptExists(sha1);
    }

    @Override
    public byte[] scriptLoad(byte[] script) {
        return super.scriptLoad(script);
    }

    @Override
    public String scriptKill() {
        return super.scriptKill();
    }

    @Override
    public String slowlogReset() {
        return super.slowlogReset();
    }

    @Override
    public Long slowlogLen() {
        return super.slowlogLen();
    }

    @Override
    public List<byte[]> slowlogGetBinary() {
        return super.slowlogGetBinary();
    }

    @Override
    public List<byte[]> slowlogGetBinary(long entries) {
        return super.slowlogGetBinary(entries);
    }

    @Override
    public Long objectRefcount(byte[] key) {
        return super.objectRefcount(key);
    }

    @Override
    public byte[] objectEncoding(byte[] key) {
        return super.objectEncoding(key);
    }

    @Override
    public Long objectIdletime(byte[] key) {
        return super.objectIdletime(key);
    }

    @Override
    public Long bitcount(byte[] key) {
        return super.bitcount(key);
    }

    @Override
    public Long bitcount(byte[] key, long start, long end) {
        return super.bitcount(key, start, end);
    }

    @Override
    public Long bitop(BitOP op, byte[] destKey, byte[]... srcKeys) {
        return super.bitop(op, destKey, srcKeys);
    }

    @Override
    public byte[] dump(byte[] key) {
        return super.dump(key);
    }

    @Override
    public String restore(byte[] key, int ttl, byte[] serializedValue) {
        return super.restore(key, ttl, serializedValue);
    }

    @Override
    public String restoreReplace(byte[] key, int ttl, byte[] serializedValue) {
        return super.restoreReplace(key, ttl, serializedValue);
    }

    @Override
    public Long pexpire(byte[] key, long milliseconds) {
        return super.pexpire(key, milliseconds);
    }

    @Override
    public Long pexpireAt(byte[] key, long millisecondsTimestamp) {
        return super.pexpireAt(key, millisecondsTimestamp);
    }

    @Override
    public Long pttl(byte[] key) {
        return super.pttl(key);
    }

    @Override
    public String psetex(byte[] key, long milliseconds, byte[] value) {
        return super.psetex(key, milliseconds, value);
    }

    @Override
    public byte[] memoryDoctorBinary() {
        return super.memoryDoctorBinary();
    }

    @Override
    public String clientKill(byte[] ipPort) {
        return super.clientKill(ipPort);
    }

    @Override
    public String clientKill(String ip, int port) {
        return super.clientKill(ip, port);
    }

    @Override
    public Long clientKill(ClientKillParams params) {
        return super.clientKill(params);
    }

    @Override
    public byte[] clientGetnameBinary() {
        return super.clientGetnameBinary();
    }

    @Override
    public byte[] clientListBinary() {
        return super.clientListBinary();
    }

    @Override
    public String clientSetname(byte[] name) {
        return super.clientSetname(name);
    }

    @Override
    public String clientPause(long timeout) {
        return super.clientPause(timeout);
    }

    @Override
    public List<String> time() {
        return super.time();
    }

    @Override
    public String migrate(String host, int port, byte[] key, int destinationDb, int timeout) {
        return super.migrate(host, port, key, destinationDb, timeout);
    }

    @Override
    public String migrate(String host, int port, int destinationDB, int timeout, MigrateParams params, byte[]... keys) {
        return super.migrate(host, port, destinationDB, timeout, params, keys);
    }

    @Override
    public Long waitReplicas(int replicas, long timeout) {
        return super.waitReplicas(replicas, timeout);
    }

    @Override
    public Long pfadd(byte[] key, byte[]... elements) {
        return super.pfadd(key, elements);
    }

    @Override
    public long pfcount(byte[] key) {
        return super.pfcount(key);
    }

    @Override
    public String pfmerge(byte[] destkey, byte[]... sourcekeys) {
        return super.pfmerge(destkey, sourcekeys);
    }

    @Override
    public Long pfcount(byte[]... keys) {
        return super.pfcount(keys);
    }

    @Override
    public ScanResult<byte[]> scan(byte[] cursor) {
        return super.scan(cursor);
    }

    @Override
    public ScanResult<byte[]> scan(byte[] cursor, ScanParams params) {
        return super.scan(cursor, params);
    }

    @Override
    public ScanResult<Map.Entry<byte[], byte[]>> hscan(byte[] key, byte[] cursor) {
        return super.hscan(key, cursor);
    }

    @Override
    public ScanResult<Map.Entry<byte[], byte[]>> hscan(byte[] key, byte[] cursor, ScanParams params) {
        return super.hscan(key, cursor, params);
    }

    @Override
    public ScanResult<byte[]> sscan(byte[] key, byte[] cursor) {
        return super.sscan(key, cursor);
    }

    @Override
    public ScanResult<byte[]> sscan(byte[] key, byte[] cursor, ScanParams params) {
        return super.sscan(key, cursor, params);
    }

    @Override
    public ScanResult<Tuple> zscan(byte[] key, byte[] cursor) {
        return super.zscan(key, cursor);
    }

    @Override
    public ScanResult<Tuple> zscan(byte[] key, byte[] cursor, ScanParams params) {
        return super.zscan(key, cursor, params);
    }

    @Override
    public Long geoadd(byte[] key, double longitude, double latitude, byte[] member) {
        return super.geoadd(key, longitude, latitude, member);
    }

    @Override
    public Long geoadd(byte[] key, Map<byte[], GeoCoordinate> memberCoordinateMap) {
        return super.geoadd(key, memberCoordinateMap);
    }

    @Override
    public Double geodist(byte[] key, byte[] member1, byte[] member2) {
        return super.geodist(key, member1, member2);
    }

    @Override
    public Double geodist(byte[] key, byte[] member1, byte[] member2, GeoUnit unit) {
        return super.geodist(key, member1, member2, unit);
    }

    @Override
    public List<byte[]> geohash(byte[] key, byte[]... members) {
        return super.geohash(key, members);
    }

    @Override
    public List<GeoCoordinate> geopos(byte[] key, byte[]... members) {
        return super.geopos(key, members);
    }

    @Override
    public List<GeoRadiusResponse> georadius(byte[] key, double longitude, double latitude, double radius, GeoUnit unit) {
        return super.georadius(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(byte[] key, double longitude, double latitude, double radius, GeoUnit unit) {
        return super.georadiusReadonly(key, longitude, latitude, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadius(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return super.georadius(key, longitude, latitude, radius, unit, param);
    }

    @Override
    public List<GeoRadiusResponse> georadiusReadonly(byte[] key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return super.georadiusReadonly(key, longitude, latitude, radius, unit, param);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(byte[] key, byte[] member, double radius, GeoUnit unit) {
        return super.georadiusByMember(key, member, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(byte[] key, byte[] member, double radius, GeoUnit unit) {
        return super.georadiusByMemberReadonly(key, member, radius, unit);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return super.georadiusByMember(key, member, radius, unit, param);
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMemberReadonly(byte[] key, byte[] member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return super.georadiusByMemberReadonly(key, member, radius, unit, param);
    }

    @Override
    public List<Long> bitfield(byte[] key, byte[]... arguments) {
        return super.bitfield(key, arguments);
    }

    @Override
    public Long hstrlen(byte[] key, byte[] field) {
        return super.hstrlen(key, field);
    }

    @Override
    public List<byte[]> xread(int count, long block, Map<byte[], byte[]> streams) {
        return super.xread(count, block, streams);
    }

    @Override
    public List<byte[]> xreadGroup(byte[] groupname, byte[] consumer, int count, long block, boolean noAck, Map<byte[], byte[]> streams) {
        return super.xreadGroup(groupname, consumer, count, block, noAck, streams);
    }

    @Override
    public byte[] xadd(byte[] key, byte[] id, Map<byte[], byte[]> hash, long maxLen, boolean approximateLength) {
        return super.xadd(key, id, hash, maxLen, approximateLength);
    }

    @Override
    public Long xlen(byte[] key) {
        return super.xlen(key);
    }

    @Override
    public List<byte[]> xrange(byte[] key, byte[] start, byte[] end, long count) {
        return super.xrange(key, start, end, count);
    }

    @Override
    public List<byte[]> xrevrange(byte[] key, byte[] end, byte[] start, int count) {
        return super.xrevrange(key, end, start, count);
    }

    @Override
    public Long xack(byte[] key, byte[] group, byte[]... ids) {
        return super.xack(key, group, ids);
    }

    @Override
    public String xgroupCreate(byte[] key, byte[] consumer, byte[] id, boolean makeStream) {
        return super.xgroupCreate(key, consumer, id, makeStream);
    }

    @Override
    public String xgroupSetID(byte[] key, byte[] consumer, byte[] id) {
        return super.xgroupSetID(key, consumer, id);
    }

    @Override
    public Long xgroupDestroy(byte[] key, byte[] consumer) {
        return super.xgroupDestroy(key, consumer);
    }

    @Override
    public String xgroupDelConsumer(byte[] key, byte[] consumer, byte[] consumerName) {
        return super.xgroupDelConsumer(key, consumer, consumerName);
    }

    @Override
    public Long xdel(byte[] key, byte[]... ids) {
        return super.xdel(key, ids);
    }

    @Override
    public Long xtrim(byte[] key, long maxLen, boolean approximateLength) {
        return super.xtrim(key, maxLen, approximateLength);
    }

    @Override
    public List<byte[]> xpending(byte[] key, byte[] groupname, byte[] start, byte[] end, int count, byte[] consumername) {
        return super.xpending(key, groupname, start, end, count, consumername);
    }

    @Override
    public List<byte[]> xclaim(byte[] key, byte[] groupname, byte[] consumername, long minIdleTime, long newIdleTime, int retries, boolean force, byte[][] ids) {
        return super.xclaim(key, groupname, consumername, minIdleTime, newIdleTime, retries, force, ids);
    }

    @Override
    public Object sendCommand(ProtocolCommand cmd, byte[]... args) {
        return super.sendCommand(cmd, args);
    }

    @Override
    public Object sendCommand(ProtocolCommand cmd) {
        return super.sendCommand(cmd);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
