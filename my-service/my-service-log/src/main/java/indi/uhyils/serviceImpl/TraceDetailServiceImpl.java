package indi.uhyils.serviceImpl;

import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.annotation.NoToken;
import indi.uhyils.content.Content;
import indi.uhyils.dao.TraceDetailDao;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.*;
import indi.uhyils.service.TraceDetailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
@RpcService
public class TraceDetailServiceImpl extends BaseDefaultServiceImpl<TraceDetailEntity> implements TraceDetailService {

    @Autowired
    private TraceDetailDao dao;


    @Override
    public TraceDetailDao getDao() {
        return dao;
    }

    public void setDao(TraceDetailDao dao) {
        this.dao = dao;
    }
}
