package indi.uhyils.serviceImpl;

import indi.uhyils.dao.InstructionsDao;
import indi.uhyils.pojo.model.InstructionsDO;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.InstructionsService;

import javax.annotation.Resource;


/**
 * (Instructions)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分50秒
 */
@RpcService
public class InstructionsServiceImpl extends BaseDefaultServiceImpl<InstructionsDO> implements InstructionsService {
    @Resource
    private InstructionsDao dao;

    @Override
    public InstructionsDao getDao() {
        return this.dao;
    }

    public void setDao(InstructionsDao dao) {
        this.dao = dao;
    }


}
