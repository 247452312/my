package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.RedisDao;
import indi.uhyils.pojo.model.RedisEntity;
import indi.uhyils.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 12时55分
 */
@Service(group = "${spring.profiles.active}")
public class RedisServiceImpl extends BaseDefaultServiceImpl<RedisEntity> implements RedisService {

    @Autowired
    private RedisDao dao;

    public RedisDao getDao() {
        return dao;
    }

    public void setDao(RedisDao dao) {
        this.dao = dao;
    }
}
