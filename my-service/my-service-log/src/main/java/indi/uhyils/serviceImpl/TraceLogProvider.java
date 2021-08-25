package indi.uhyils.serviceImpl;

import indi.uhyils.dao.TraceLogDao;
import indi.uhyils.pojo.DO.TraceLogDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
@RpcService
public class TraceLogProvider extends BaseDefaultProvider<TraceLogDO> implements indi.uhyils.protocol.rpc.provider.TraceLogProvider {

    @Autowired
    private TraceLogDao dao;


    @Override
    public TraceLogDao getDao() {
        return dao;
    }

    public void setDao(TraceLogDao dao) {
        this.dao = dao;
    }
}
