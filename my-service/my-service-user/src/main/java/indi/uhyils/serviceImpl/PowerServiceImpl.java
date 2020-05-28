package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.PowerDao;
import indi.uhyils.pojo.model.PowerEntity;
import indi.uhyils.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@Service(group = "${spring.profiles.active}")
public class PowerServiceImpl extends DefaultServiceImpl<PowerEntity> implements PowerService {


    @Autowired
    private PowerDao dao;

    public PowerDao getDao() {
        return dao;
    }

    public void setDao(PowerDao dao) {
        this.dao = dao;
    }
}
