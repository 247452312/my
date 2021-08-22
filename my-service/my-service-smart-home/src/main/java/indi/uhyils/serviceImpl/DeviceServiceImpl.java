package indi.uhyils.serviceImpl;

import indi.uhyils.dao.DeviceDao;
import indi.uhyils.pojo.model.DeviceDO;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.DeviceService;

import javax.annotation.Resource;


/**
 * (Device)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分40秒
 */
@RpcService
public class DeviceServiceImpl extends BaseDefaultServiceImpl<DeviceDO> implements DeviceService {
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
