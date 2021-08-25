package indi.uhyils.serviceImpl;

import indi.uhyils.dao.SceneDao;
import indi.uhyils.pojo.DO.SceneDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;


/**
 * (Scene)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分53秒
 */
@RpcService
public class SceneProvider extends BaseDefaultProvider<SceneDO> implements indi.uhyils.protocol.rpc.provider.SceneProvider {
    @Resource
    private SceneDao dao;

    @Override
    public SceneDao getDao() {
        return this.dao;
    }

    public void setDao(SceneDao dao) {
        this.dao = dao;
    }


}
