package indi.uhyils.serviceImpl;

import indi.uhyils.dao.DeviceCallbackDao;
import indi.uhyils.pojo.model.DeviceCallbackEntity;
import indi.uhyils.service.DeviceCallbackService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * (DeviceCallback)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分48秒
 */
@Service(group = "${spring.profiles.active}")
public class DeviceCallbackServiceImpl extends BaseDefaultServiceImpl<DeviceCallbackEntity> implements DeviceCallbackService {
    @Autowired
    private DeviceCallbackDao dao;

    public DeviceCallbackDao getDao() {
        return this.dao;
    }

    public void setDao(DeviceCallbackDao dao) {
        this.dao = dao;
    }


}
