package indi.uhyils.serviceImpl;

import indi.uhyils.dao.SpaceDao;
import indi.uhyils.pojo.DO.SpaceDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;


/**
 * (Space)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分54秒
 */
@RpcService
public class SpaceProvider extends BaseDefaultProvider<SpaceDO> implements indi.uhyils.protocol.rpc.provider.SpaceProvider {
    @Resource
    private SpaceDao dao;

    @Override
    public SpaceDao getDao() {
        return this.dao;
    }

    public void setDao(SpaceDao dao) {
        this.dao = dao;
    }


}
