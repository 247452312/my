package indi.uhyils.serviceImpl;

import indi.uhyils.dao.InstructionsDao;
import indi.uhyils.pojo.DO.InstructionsDO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;

import javax.annotation.Resource;


/**
 * (Instructions)表 服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分50秒
 */
@RpcService
public class InstructionsProvider extends BaseDefaultProvider<InstructionsDO> implements indi.uhyils.protocol.rpc.provider.InstructionsProvider {
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
