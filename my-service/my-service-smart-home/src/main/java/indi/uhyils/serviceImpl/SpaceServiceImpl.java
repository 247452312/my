package indi.uhyils.serviceImpl;

import indi.uhyils.dao.SpaceDao;
import indi.uhyils.pojo.model.SpaceEntity;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.SpaceService;

import javax.annotation.Resource;


/**
 * (Space)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分54秒
 */
@RpcService
public class SpaceServiceImpl extends BaseDefaultServiceImpl<SpaceEntity> implements SpaceService {
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
