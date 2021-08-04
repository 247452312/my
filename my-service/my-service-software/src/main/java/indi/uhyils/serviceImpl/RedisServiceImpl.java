package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.RedisDao;
import indi.uhyils.dao.ServerDao;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.enum_.RedisAddEnum;
import indi.uhyils.enum_.RedisUpdateEnum;
import indi.uhyils.enum_.SoftwareStatusEnum;
import indi.uhyils.pojo.model.RedisEntity;
import indi.uhyils.pojo.model.ServerEntity;
import indi.uhyils.pojo.request.GetRedisKeysRequest;
import indi.uhyils.pojo.request.RedisKeyAndValue;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.request.base.IdsRequest;
import indi.uhyils.pojo.request.base.ObjRequest;
import indi.uhyils.pojo.response.GetInfosResponse;
import indi.uhyils.pojo.response.OperateSoftwareResponse;
import indi.uhyils.pojo.response.RedisKeyResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.RedisService;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SshUtils;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 12时55分
 */
@RpcService
public class RedisServiceImpl extends BaseDefaultServiceImpl<RedisEntity> implements RedisService {

    /**
     * 获取的redis info中的注释
     */
    private static final String REDIS_NOTES = "#";

    /**
     * redis info 根据:切分信息时不能以:结尾.会导致错误
     */
    private static final String ERROR_END_WITH = ":";

    /**
     * docker启动中的redis返回值
     */
    private static final String DOCKER_REDIS_STATUS_RUNNING = "true";

    /**
     * redis 获取属性时list的size
     */
    private static final Integer CONFIG_GET_SIZE = 2;

    @Resource
    private RedisDao dao;

    @Resource
    private ServerDao serverDao;

    @Override
    public RedisDao getDao() {
        return dao;
    }

    public void setDao(RedisDao dao) {
        this.dao = dao;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> insert(ObjRequest<RedisEntity> insert) throws Exception {
        RedisEntity redisEntity = insert.getData();
        ServerEntity serverEntity = serverDao.getById(redisEntity.getServerId());
        if (serverEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null);
        }
        String redisVersion = getRedisNewVersion(redisEntity, serverEntity);
        redisEntity.setVersion(redisVersion);
        redisEntity.setStatus(getRedisNewStatus(redisEntity, serverEntity));
        redisEntity.preInsert(insert);
        dao.insert(redisEntity);
        return ServiceResult.buildSuccessResult("插入成功", 1);

    }

    @Override
    public ServiceResult<RedisEntity> reload(IdRequest request) {
        Long id = request.getId();
        RedisEntity redisEntity = dao.getById(id);
        if (redisEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null);
        }
        Long serverId = redisEntity.getServerId();
        ServerEntity serverEntity = serverDao.getById(serverId);
        if (serverEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null);
        }
        redisEntity.setStatus(getRedisNewStatus(redisEntity, serverEntity));
        String redisVersion = getRedisNewVersion(redisEntity, serverEntity);
        redisEntity.setVersion(redisVersion);
        redisEntity.preUpdate(request);
        dao.update(redisEntity);
        return ServiceResult.buildSuccessResult("刷新状态成功", redisEntity);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<OperateSoftwareResponse> start(IdRequest request) {
        OperateSoftwareResponse operateSoftwareResponse = new OperateSoftwareResponse();
        RedisEntity redisEntity = dao.getById(request.getId());
        Long serverId = redisEntity.getServerId();
        ServerEntity serverEntity = serverDao.getById(serverId);
        Integer redisNewStatus = getRedisNewStatus(redisEntity, serverEntity);
        if (SoftwareStatusEnum.RUNNING.getStatus().equals(redisNewStatus)) {
            operateSoftwareResponse.setStatus(SoftwareStatusEnum.RUNNING.getStatus());
            operateSoftwareResponse.setMsg("redis运行中,不需要启动");
            return ServiceResult.buildSuccessResult("启动成功", operateSoftwareResponse);
        }
        String s = SshUtils.execCommandBySsh(serverEntity, redisEntity.getStartSh());
        if (StringUtils.contains(s, redisEntity.getDockerName())) {
            operateSoftwareResponse.setStatus(SoftwareStatusEnum.RUNNING.getStatus());
            operateSoftwareResponse.setMsg("redis docker启动成功");
            return ServiceResult.buildSuccessResult("启动成功", operateSoftwareResponse);
        }
        return ServiceResult.buildFailedResult("启动失败", null);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<OperateSoftwareResponse> stop(IdRequest request) {
        OperateSoftwareResponse operateSoftwareResponse = new OperateSoftwareResponse();
        RedisEntity redisEntity = dao.getById(request.getId());
        Long serverId = redisEntity.getServerId();
        ServerEntity serverEntity = serverDao.getById(serverId);
        Integer redisNewStatus = getRedisNewStatus(redisEntity, serverEntity);
        if (SoftwareStatusEnum.STOP.getStatus().equals(redisNewStatus)) {
            operateSoftwareResponse.setStatus(SoftwareStatusEnum.STOP.getStatus());
            operateSoftwareResponse.setMsg("redis已经停止,不需要再次停止");
            return ServiceResult.buildSuccessResult("停止成功", operateSoftwareResponse);
        }
        String s = SshUtils.execCommandBySsh(serverEntity, redisEntity.getStopSh());
        if (StringUtils.contains(s, redisEntity.getDockerName())) {
            operateSoftwareResponse.setStatus(SoftwareStatusEnum.STOP.getStatus());
            operateSoftwareResponse.setMsg("redis docker停止成功");
            return ServiceResult.buildSuccessResult("停止成功", operateSoftwareResponse);
        }
        return ServiceResult.buildFailedResult("停止失败", null);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> deleteManyRedis(IdsRequest request) {
        List<Long> ids = request.getIds();
        List<RedisEntity> collect = ids.stream().map(t -> dao.getById(t)).collect(Collectors.toList());
        AtomicBoolean b = new AtomicBoolean(true);
        collect.forEach(t -> {
            t.setDeleteFlag(true);
            t.preUpdate(request);
            int update = dao.update(t);
            if (update == 0) {
                b.set(Boolean.FALSE);
            }
        });
        return ServiceResult.buildSuccessResult("删除redis执行成功", b.get());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> reloadManyRedis(IdsRequest request) {
        List<Long> ids = request.getIds();
        AtomicBoolean b = new AtomicBoolean(true);
        ids.forEach(id -> {
            try {
                RedisEntity redisEntity = dao.getById(id);
                Long serverId = redisEntity.getServerId();
                ServerEntity serverEntity = serverDao.getById(serverId);
                redisEntity.setStatus(getRedisNewStatus(redisEntity, serverEntity));
                String redisVersion = getRedisNewVersion(redisEntity, serverEntity);
                redisEntity.setVersion(redisVersion);
                redisEntity.preUpdate(request);
                dao.update(redisEntity);
            } catch (Exception e) {
                LogUtil.error(this, e);
                b.set(Boolean.FALSE);
            }
        });

        return ServiceResult.buildSuccessResult("重置数据操作成功", b.get());

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> startManyRedis(IdsRequest request) {
        List<Long> ids = request.getIds();
        AtomicBoolean b = new AtomicBoolean(true);
        ids.forEach(id -> {
            RedisEntity redisEntity = dao.getById(id);
            Long serverId = redisEntity.getServerId();
            ServerEntity serverEntity = serverDao.getById(serverId);
            Integer redisNewStatus = getRedisNewStatus(redisEntity, serverEntity);
            if (SoftwareStatusEnum.RUNNING.getStatus().equals(redisNewStatus)) {
                return;
            }
            String s = SshUtils.execCommandBySsh(serverEntity, redisEntity.getStartSh());
            if (StringUtils.contains(s, redisEntity.getDockerName())) {
                return;
            }
            b.set(Boolean.FALSE);
        });
        return ServiceResult.buildSuccessResult("批量开启redis执行成功", b.get());

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> stopManyRedis(IdsRequest request) {
        AtomicBoolean b = new AtomicBoolean(true);
        request.getIds().forEach(id -> {
            RedisEntity redisEntity = dao.getById(id);
            Long serverId = redisEntity.getServerId();
            ServerEntity serverEntity = serverDao.getById(serverId);
            Integer redisNewStatus = getRedisNewStatus(redisEntity, serverEntity);
            if (SoftwareStatusEnum.STOP.getStatus().equals(redisNewStatus)) {
                return;
            }
            String s = SshUtils.execCommandBySsh(serverEntity, redisEntity.getStopSh());
            if (StringUtils.contains(s, redisEntity.getDockerName())) {
                return;
            }
            b.set(Boolean.FALSE);
        });

        return ServiceResult.buildSuccessResult("redis批量停止操作执行成功", b.get());

    }

    @Override
    public ServiceResult<ArrayList<RedisKeyResponse>> getRedisKeys(GetRedisKeysRequest request) {
        Integer db = request.getDb();
        Long id = request.getRedisId();
        Jedis jedis = getJedis(id);
        jedis.select(db);

        Set<String> keys = jedis.keys("*");
        jedis.close();
        ArrayList<RedisKeyResponse> list = new ArrayList<>();
        for (String key : keys) {
            list.add(RedisKeyResponse.build(key));
        }
        return ServiceResult.buildSuccessResult("查询key成功", list);
    }


    @Override
    public ServiceResult<Integer> getRedisDb(IdRequest request) {
        Long id = request.getId();
        Jedis jedis = getJedis(id);
        List<String> databases = jedis.configGet("databases");
        jedis.close();
        LogUtil.info(this, "查询 configGet数量为:" + databases.size());
        if (databases.size() == CONFIG_GET_SIZE) {
            String count = databases.get(1);
            return ServiceResult.buildSuccessResult("查询数据库成功", Integer.parseInt(count));
        }

        return ServiceResult.buildSuccessResult("查询数据库出问题了,查看日志", null);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> addKey(ObjRequest<RedisKeyAndValue> request) {
        RedisKeyAndValue data = request.getData();
        Long redisId = data.getRedisId();
        Jedis jedis = getJedis(redisId);
        jedis.select(data.getDb());
        String s = jedis.get(data.getKey());
        // 查询出来的不为空
        if (!StringUtils.isEmpty(s)) {
            jedis.close();
            return ServiceResult.buildSuccessResult("redis中已经存在此key了", RedisAddEnum.HAVE_KEY.getType());
        }
        jedis.append(data.getKey(), data.getValue());
        jedis.close();

        return ServiceResult.buildSuccessResult("插入成功", RedisAddEnum.SUCCESS.getType());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> addKeyCover(ObjRequest<RedisKeyAndValue> request) {
        RedisKeyAndValue data = request.getData();
        Long redisId = data.getRedisId();
        Jedis jedis = getJedis(redisId);
        jedis.append(data.getKey(), data.getValue());
        jedis.close();
        return ServiceResult.buildSuccessResult("添加成功", RedisAddEnum.SUCCESS.getType());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> updateKey(ObjRequest<RedisKeyAndValue> request) {
        RedisKeyAndValue data = request.getData();
        Long redisId = data.getRedisId();
        Jedis jedis = getJedis(redisId);
        String s = jedis.get(data.getKey());
        // 如果是空,则说明修改没有
        if (StringUtils.isEmpty(s)) {
            jedis.close();
            return ServiceResult.buildSuccessResult("key不存在", RedisUpdateEnum.NO_KEY.getType());
        }
        jedis.set(data.getKey(), data.getValue());
        jedis.close();
        return ServiceResult.buildSuccessResult("修改成功", RedisUpdateEnum.SUCCESS.getType());
    }

    @Override
    public ServiceResult<String> getValueByKey(ObjRequest<RedisKeyAndValue> request) {
        RedisKeyAndValue data = request.getData();
        Jedis jedis = getJedis(data.getRedisId());
        String value = jedis.get(data.getKey());
        jedis.close();
        return ServiceResult.buildSuccessResult("redis value查询成功", value);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> deleteRedisByKey(ObjRequest<RedisKeyAndValue> request) {
        RedisKeyAndValue data = request.getData();
        Long redisId = data.getRedisId();
        Jedis jedis = getJedis(redisId);
        jedis.del(data.getKey());
        jedis.close();
        return ServiceResult.buildSuccessResult("删除成功", true);
    }

    @Override
    public ServiceResult<ArrayList<GetInfosResponse>> getInfos(IdRequest request) {
        Long id = request.getId();
        Jedis jedis = getJedis(id);
        Client client = jedis.getClient();
        client.info();
        String bulkReply = client.getBulkReply();
        String[] split = bulkReply.replace("\r", "").split("\\n");
        ArrayList<GetInfosResponse> list = new ArrayList<>();
        Arrays.stream(split).forEach(t -> {
            t = t.trim();
            // # 开头属于注释 所以要去掉 :结尾会报错,所以要去掉
            if (StringUtils.isEmpty(t) || t.startsWith(REDIS_NOTES) || t.endsWith(ERROR_END_WITH)) {
                return;
            }

            String[] keyAndValue = t.split(":");
            list.add(GetInfosResponse.build(keyAndValue[0], keyAndValue[1]));

        });
        jedis.close();

        return ServiceResult.buildSuccessResult("获取redis服务器信息", list);
    }


    public Integer getRedisNewStatus(RedisEntity redisEntity, ServerEntity serverEntity) {

        String statusSh = redisEntity.getStatusSh();
        String status = SshUtils.execCommandBySsh(serverEntity.getIp(), serverEntity.getPort(), serverEntity.getUsername(), serverEntity.getPassword(), statusSh);
        if (status.contains(DOCKER_REDIS_STATUS_RUNNING)) {
            return SoftwareStatusEnum.RUNNING.getStatus();
        } else {
            return SoftwareStatusEnum.STOP.getStatus();
        }
    }

    private Jedis getJedis(Long id) {
        RedisEntity redisEntity = dao.getById(id);
        Long serverId = redisEntity.getServerId();
        ServerEntity serverEntity = serverDao.getById(serverId);
        Jedis jedis = new Jedis(serverEntity.getIp(), redisEntity.getPort());
        if (!StringUtils.isEmpty(redisEntity.getPassword())) {
            jedis.auth(redisEntity.getPassword());
        }
        return jedis;

    }

    public String getRedisNewVersion(RedisEntity redisEntity, ServerEntity serverEntity) {
        Map<String, String> redisNewInfo = getRedisNewInfo(redisEntity, serverEntity);
        return redisNewInfo.get("redis_version");
    }

    public Map<String, String> getRedisNewInfo(RedisEntity redisEntity, ServerEntity serverEntity) {
        Jedis jedis = new Jedis(serverEntity.getIp(), redisEntity.getPort());
        if (!StringUtils.isEmpty(redisEntity.getPassword())) {
            jedis.auth(redisEntity.getPassword());
        }
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
        jedis.close();
        return redisInfoMap;
    }
}
