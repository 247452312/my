package indi.uhyils.serviceImpl;

import indi.uhyils.dao.MsgDao;
import indi.uhyils.pojo.DO.MsgDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月25日 13时30分
 */
@RpcService
public class MsgProvider extends BaseDefaultProvider<MsgDO> implements indi.uhyils.protocol.rpc.provider.MsgProvider {

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
