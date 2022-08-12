package indi.uhyils.facade.impl;

import indi.uhyils.annotation.Facade;
import indi.uhyils.facade.DictFacade;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.request.GetByCodeQuery;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.DictProvider;
import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.util.DefaultCQEBuildUtil;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 10时32分
 */
@Facade
public class DictFacadeImpl implements DictFacade {


    @RpcReference
    private DictProvider dictProvider;

    @Override
    public List<DictItemDTO> getByCode(String code) {
        GetByCodeQuery request = new GetByCodeQuery();
        DefaultCQE adminDefaultCQE = DefaultCQEBuildUtil.getAdminDefaultCQE();
        request.setUser(adminDefaultCQE.getUser());
        request.setCode(code);
        return dictProvider.getByCode(request);
    }
}
