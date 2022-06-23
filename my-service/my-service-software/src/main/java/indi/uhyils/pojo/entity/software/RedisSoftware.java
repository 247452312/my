package indi.uhyils.pojo.entity.software;

import indi.uhyils.pojo.DO.SoftwareDO;
import indi.uhyils.pojo.DTO.response.GetInfosResponse;
import indi.uhyils.pojo.entity.Software;
import indi.uhyils.repository.SoftwareRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 10时36分
 */
public class RedisSoftware extends Software implements RedisSoftwareInterface {


    /**
     * redis 获取属性时list的size
     */
    private static final Integer CONFIG_GET_SIZE = 2;

    /**
     * 获取的redis info中的注释
     */
    private static final String REDIS_NOTES = "#";

    /**
     * redis info 根据:切分信息时不能以:结尾.会导致错误
     */
    private static final String ERROR_END_WITH = ":";

    private Jedis jedis;

    public RedisSoftware(SoftwareDO dO) {
        super(dO);
    }

    public RedisSoftware(Long id) {
        super(id);
    }

    public RedisSoftware(Long id, SoftwareRepository rep) {
        super(id, rep);
    }

    @Override
    public void select(Integer db) {
        initJedis();
        jedis.select(db);
    }

    public void initJedis() {
        Asserts.assertTrue(server != null, "server不能为空");
        final SoftwareDO softwareDO = toData().orElseThrow(() -> Asserts.makeException("未找到data"));
        if (jedis == null) {
            jedis = new Jedis(server.toData().orElseThrow(() -> Asserts.makeException("未找到data")).getIp(), softwareDO.getPort());
        }
        jedis.auth(softwareDO.getPassword());
    }

    @Override
    public List<GetInfosResponse> findRedisInfo() {
        initJedis();
        Map<String, String> redisNewInfo = getRedisNewInfo();
        return redisNewInfo.entrySet().stream().map(t1 -> GetInfosResponse.build(t1.getKey(), t1.getValue())).collect(Collectors.toList());
    }

    @Override
    public void close() {
        if (jedis != null) {
            jedis.close();
            jedis = null;
        }
    }

    @Override
    public void initBaseInfo() {
        String redisVersion = getRedisNewVersion();
        final SoftwareDO softwareDO = toData().orElseThrow(() -> Asserts.makeException("未找到data"));
        softwareDO.setVersion(redisVersion);
        softwareDO.setStatus(getStatus().getStatus());
    }

    @Override
    public Integer findRedisDb() {
        initJedis();
        List<String> databases = jedis.configGet("databases");
        LogUtil.info(this, "查询 configGet数量为:" + databases.size());
        if (databases.size() == CONFIG_GET_SIZE) {
            String count = databases.get(1);
            return Integer.parseInt(count);
        }
        return null;
    }

    @Override
    public void cas(String key, String value) {
        initJedis();
        String s = jedis.get(key);
        if (StringUtils.isNotEmpty(s)) {
            return;
        }
        jedis.set(key, value);
    }

    @Override
    public void forcePut(String key, String value) {
        initJedis();
        jedis.set(key, value);
    }

    @Override
    public void update(String key, String value) {
        initJedis();
        jedis.set(key, value);
    }

    @Override
    public String get(String key) {
        initJedis();
        return jedis.get(key);
    }

    @Override
    public void del(String key) {
        initJedis();
        jedis.del(key);
    }

    private String getRedisNewVersion() {
        Map<String, String> redisNewInfo = getRedisNewInfo();
        return redisNewInfo.get("redis_version");
    }

    private Map<String, String> getRedisNewInfo() {
        initJedis();
        Client client = jedis.getClient();
        client.info();
        String bulkReply = client.getBulkReply();
        String[] split = bulkReply.replace("\r", "").split("\\n");
        HashMap<String, String> redisInfoMap = new HashMap<>(split.length);
        Arrays.stream(split).forEach(t -> {
            t = t.trim();
            // # 开头属于注释 所以要去掉 :结尾会报错,所以要去掉
            if (StringUtils.isEmpty(t) || t.startsWith(REDIS_NOTES) || t.endsWith(ERROR_END_WITH)) {
                return;
            }
            String[] keyAndValue = t.split(":");
            redisInfoMap.put(keyAndValue[0], keyAndValue[1]);

        });
        return redisInfoMap;
    }
}
