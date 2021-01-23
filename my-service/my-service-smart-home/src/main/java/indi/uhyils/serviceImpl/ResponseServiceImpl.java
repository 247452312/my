package indi.uhyils.serviceImpl;

import indi.uhyils.dao.ResponseDao;
import indi.uhyils.pojo.model.ResponseEntity;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.ResponseService;

import javax.annotation.Resource;


/**
 * (Response)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分52秒
 */
@RpcService
public class ResponseServiceImpl extends BaseDefaultServiceImpl<ResponseEntity> implements ResponseService {
    @Resource
    private ResponseDao dao;

    @Override
    public ResponseDao getDao() {
        return this.dao;
    }

    public void setDao(ResponseDao dao) {
        this.dao = dao;
    }


}
