package indi.uhyils.serviceImpl;

import indi.uhyils.dao.DeviceCallbackDao;
import indi.uhyils.pojo.model.DeviceCallbackEntity;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.DeviceCallbackService;

import javax.annotation.Resource;


/**
 * (DeviceCallback)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分48秒
 */
@RpcService
public class DeviceCallbackServiceImpl extends BaseDefaultServiceImpl<DeviceCallbackEntity> implements DeviceCallbackService {
    @Resource
    private DeviceCallbackDao dao;

    @Override
    public DeviceCallbackDao getDao() {
        return this.dao;
    }

    public void setDao(DeviceCallbackDao dao) {
        this.dao = dao;
    }


}
