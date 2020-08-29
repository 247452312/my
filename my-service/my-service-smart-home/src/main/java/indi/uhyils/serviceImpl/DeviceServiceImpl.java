package indi.uhyils.serviceImpl;

import indi.uhyils.dao.DeviceDao;
import indi.uhyils.pojo.model.DeviceEntity;
import indi.uhyils.service.DeviceService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * (Device)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分40秒
 */
@Service(group = "${spring.profiles.active}")
public class DeviceServiceImpl extends BaseDefaultServiceImpl<DeviceEntity> implements DeviceService {
    @Autowired
    private DeviceDao dao;

    public DeviceDao getDao() {
        return this.dao;
    }

    public void setDao(DeviceDao dao) {
        this.dao = dao;
    }


}
