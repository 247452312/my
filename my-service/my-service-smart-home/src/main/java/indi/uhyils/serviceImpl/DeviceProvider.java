package indi.uhyils.serviceImpl;

import indi.uhyils.dao.DeviceDao;
import indi.uhyils.pojo.DO.DeviceDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;


/**
 * (Device)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分40秒
 */
@RpcService
public class DeviceProvider extends BaseDefaultProvider<DeviceDO> implements indi.uhyils.protocol.rpc.provider.DeviceProvider {
    @Resource
    private DeviceDao dao;

    @Override
    public DeviceDao getDao() {
        return this.dao;
    }

    public void setDao(DeviceDao dao) {
        this.dao = dao;
    }


}
