package indi.uhyils.serviceImpl;

import indi.uhyils.dao.MsgDao;
import indi.uhyils.pojo.model.MsgEntity;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.MsgService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@RpcService
public class MsgServiceImpl extends BaseDefaultServiceImpl<MsgEntity> implements MsgService {

    @Resource
    private MsgDao dao;

    @Override
    public MsgDao getDao() {
        return dao;
    }

    public void setDao(MsgDao dao) {
        this.dao = dao;
    }
}
