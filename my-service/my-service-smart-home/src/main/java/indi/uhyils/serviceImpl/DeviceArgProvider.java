package indi.uhyils.serviceImpl;

import indi.uhyils.dao.DeviceArgDao;
import indi.uhyils.pojo.DO.DeviceArgDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;


/**
 * (DeviceArg)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分42秒
 */
@RpcService
public class DeviceArgProvider extends BaseDefaultProvider<DeviceArgDO> implements indi.uhyils.protocol.rpc.provider.DeviceArgProvider {
    @Resource
    private DeviceArgDao dao;

    @Override
    public DeviceArgDao getDao() {
        return this.dao;
    }

    public void setDao(DeviceArgDao dao) {
        this.dao = dao;
    }


}
