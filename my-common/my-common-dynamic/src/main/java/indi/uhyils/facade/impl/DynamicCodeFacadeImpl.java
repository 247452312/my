package indi.uhyils.facade.impl;

import indi.uhyils.annotation.Facade;
import indi.uhyils.enum_.Symbol;
import indi.uhyils.facade.DynamicCodeFacade;
import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.query.BlackQuery;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.protocol.rpc.DynamicCodeProvider;
import indi.uhyils.rpc.annotation.RpcReference;
import java.util.Collections;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月11日 20时12分
 */
@Facade
public class DynamicCodeFacadeImpl implements DynamicCodeFacade {

    @RpcReference
    private DynamicCodeProvider provider;

    @Override
    public List<DynamicCodeDTO> getByGroupId(Identifier groupId) {
        BlackQuery query = new BlackQuery();
        query.setArgs(Collections.singletonList(Arg.as(DynamicCodeDTO::getGroupId, Symbol.EQ, groupId.getId())));
        ServiceResult<List<DynamicCodeDTO>> result = provider.queryNoPage(query);
        return result.validationAndGet();
    }
}
