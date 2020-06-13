package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.RedisDao;
import indi.uhyils.dao.ServerDao;
import indi.uhyils.enum_.SoftwareStatusEnum;
import indi.uhyils.pojo.model.RedisEntity;
import indi.uhyils.pojo.model.ServerEntity;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.response.OperateSoftwareResponse;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.RedisService;
import indi.uhyils.util.SshUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 12时55分
 */
@Service(group = "${spring.profiles.active}")
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

    @Autowired
    private RedisDao dao;

    @Autowired
    private ServerDao serverDao;

    public RedisDao getDao() {
        return dao;
    }

    public void setDao(RedisDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<RedisEntity> insert) {
        RedisEntity redisEntity = insert.getData();
        ServerEntity serverEntity = serverDao.getById(redisEntity.getServerId());
        if (serverEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null, insert);
        }
        String redisVersion = getRedisNewVersion(redisEntity, serverEntity);
        redisEntity.setVersion(redisVersion);
        redisEntity.setStatus(getRedisNewStatus(redisEntity, serverEntity));
        redisEntity.preInsert(insert);
        dao.insert(redisEntity);
        return ServiceResult.buildSuccessResult("插入成功", 1, insert);

    }

    @Override
    public ServiceResult<RedisEntity> reload(IdRequest request) {
        String id = request.getId();
        RedisEntity redisEntity = dao.getById(id);
        if (redisEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null, request);
        }
        String serverId = redisEntity.getServerId();
        ServerEntity serverEntity = serverDao.getById(serverId);
        if (serverEntity == null) {
            return ServiceResult.buildFailedResult("查询失败", null, request);
        }
        redisEntity.setStatus(getRedisNewStatus(redisEntity, serverEntity));
        String redisVersion = getRedisNewVersion(redisEntity, serverEntity);
        redisEntity.setVersion(redisVersion);
        redisEntity.preUpdate(request);
        dao.update(redisEntity);
        return ServiceResult.buildSuccessResult("刷新状态成功", redisEntity, request);
    }

    @Override
    public ServiceResult<OperateSoftwareResponse> start(IdRequest request) {
        OperateSoftwareResponse operateSoftwareResponse = new OperateSoftwareResponse();
        RedisEntity redisEntity = dao.getById(request.getId());
        String serverId = redisEntity.getServerId();
        ServerEntity serverEntity = serverDao.getById(serverId);
        Integer redisNewStatus = getRedisNewStatus(redisEntity, serverEntity);
        if (SoftwareStatusEnum.RUNNING.getStatus().equals(redisNewStatus)) {
            operateSoftwareResponse.setStatus(SoftwareStatusEnum.RUNNING.getStatus());
            operateSoftwareResponse.setMsg("redis运行中,不需要启动");
            return ServiceResult.buildSuccessResult("启动成功", operateSoftwareResponse, request);
        }
        String s = SshUtils.execCommandBySsh(serverEntity, redisEntity.getStartSh());
        if (StringUtils.contains(s, redisEntity.getDockerName())) {
            operateSoftwareResponse.setStatus(SoftwareStatusEnum.RUNNING.getStatus());
            operateSoftwareResponse.setMsg("redis docker启动成功");
            return ServiceResult.buildSuccessResult("启动成功", operateSoftwareResponse, request);
        }
        return ServiceResult.buildFailedResult("启动失败", null, request);
    }

    @Override
    public ServiceResult<OperateSoftwareResponse> stop(IdRequest request) {
        OperateSoftwareResponse operateSoftwareResponse = new OperateSoftwareResponse();
        RedisEntity redisEntity = dao.getById(request.getId());
        String serverId = redisEntity.getServerId();
        ServerEntity serverEntity = serverDao.getById(serverId);
        Integer redisNewStatus = getRedisNewStatus(redisEntity, serverEntity);
        if (SoftwareStatusEnum.STOP.getStatus().equals(redisNewStatus)) {
            operateSoftwareResponse.setStatus(SoftwareStatusEnum.STOP.getStatus());
            operateSoftwareResponse.setMsg("redis已经停止,不需要再次停止");
            return ServiceResult.buildSuccessResult("停止成功", operateSoftwareResponse, request);
        }
        String s = SshUtils.execCommandBySsh(serverEntity, redisEntity.getStopSh());
        if (StringUtils.contains(s, redisEntity.getDockerName())) {
            operateSoftwareResponse.setStatus(SoftwareStatusEnum.STOP.getStatus());
            operateSoftwareResponse.setMsg("redis docker停止成功");
            return ServiceResult.buildSuccessResult("停止成功", operateSoftwareResponse, request);
        }
        return ServiceResult.buildFailedResult("停止失败", null, request);
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

    public String getRedisNewVersion(RedisEntity redisEntity, ServerEntity serverEntity) {
        Map<String, String> redisNewInfo = getRedisNewInfo(redisEntity, serverEntity);
        return redisNewInfo.get("redis_version");
    }

    public Map<String, String> getRedisNewInfo(RedisEntity redisEntity, ServerEntity serverEntity) {
        Jedis jedis = new Jedis(serverEntity.getIp(), redisEntity.getPort());
        if (!StringUtils.isBlank(redisEntity.getPassword())) {
            jedis.auth(redisEntity.getPassword());
        }
        Client client = jedis.getClient();
        client.info();
        String bulkReply = client.getBulkReply();
        String[] split = bulkReply.replace("\r", "").split("\\n");
        HashMap<String, String> redisInfoMap = new HashMap<>();
        Arrays.stream(split).forEach(t -> {
            t = t.trim();
            // # 开头属于注释 所以要去掉 :结尾会报错,所以要去掉
            if (StringUtils.isBlank(t) || t.startsWith(REDIS_NOTES) || t.endsWith(ERROR_END_WITH)) {
                return;
            }

            String[] keyAndValue = t.split(":");
            redisInfoMap.put(keyAndValue[0], keyAndValue[1]);

        });
        jedis.close();
        return redisInfoMap;
    }
}
