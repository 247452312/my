package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.RedisDao;
import indi.uhyils.dao.ServerDao;
import indi.uhyils.enum_.SoftwareStatusEnum;
import indi.uhyils.pojo.model.RedisEntity;
import indi.uhyils.pojo.model.ServerEntity;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.RedisService;
import indi.uhyils.util.SshUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        RedisEntity data = insert.getData();
        List<ServerEntity> byId = serverDao.getById(data.getServerId());
        if (byId == null || byId.size() != 1) {
            return ServiceResult.buildFailedResult("查询失败", null, insert);
        }
        ServerEntity serverEntity = byId.get(0);
        Jedis jedis = new Jedis(serverEntity.getIp(), data.getPort());
        if (!StringUtils.isBlank(data.getPassword())) {
            jedis.auth(data.getPassword());
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
        String redisVersion = redisInfoMap.get("redis_version");
        data.setVersion(redisVersion);
        String status = SshUtils.execCommandBySsh(serverEntity.getIp(), serverEntity.getPort(), serverEntity.getUsername(), serverEntity.getPassword(), data.getStatusSh());
        if (status.contains(DOCKER_REDIS_STATUS_RUNNING)) {
            data.setStatus(SoftwareStatusEnum.RUNNING.getStatus());
        }
        data.preInsert(insert);
        dao.insert(data);
        return ServiceResult.buildSuccessResult("插入成功", 1, insert);

    }
}
