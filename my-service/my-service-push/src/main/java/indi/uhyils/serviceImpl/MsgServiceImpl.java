package indi.uhyils.serviceImpl;

import indi.uhyils.dao.MsgDao;
import indi.uhyils.pojo.model.MsgEntity;
import indi.uhyils.service.MsgService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@Service(group = "${spring.profiles.active}")
public class MsgServiceImpl extends BaseDefaultServiceImpl<MsgEntity> implements MsgService {

    @Autowired
    private MsgDao dao;

    public MsgDao getDao() {
        return dao;
    }

    public void setDao(MsgDao dao) {
        this.dao = dao;
    }
}
