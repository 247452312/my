package indi.uhyils.serviceImpl;

import indi.uhyils.dao.DeviceCallbackDao;
import indi.uhyils.pojo.DO.DeviceCallbackDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;


/**
 * (DeviceCallback)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分48秒
 */
@RpcService
public class DeviceCallbackProvider extends BaseDefaultProvider<DeviceCallbackDO> implements indi.uhyils.protocol.rpc.provider.DeviceCallbackProvider {
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
