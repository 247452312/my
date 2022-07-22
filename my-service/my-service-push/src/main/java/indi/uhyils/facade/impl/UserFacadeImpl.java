package indi.uhyils.facade.impl;

import indi.uhyils.annotation.Facade;
import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.protocol.rpc.UserProvider;
import indi.uhyils.rpc.annotation.RpcReference;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月04日 08时52分
 */
@Facade
public class UserFacadeImpl implements UserFacade {

    @RpcReference
    private UserProvider userProvider;

    @Override
    public UserDTO getById(Identifier userId) {
        return userProvider.getUserById(new IdQuery(userId.getId()));
    }

    @Override
    public List<UserDTO> getByIds(List<Long> userIds) {
        return userProvider.getSampleUserByIds(new IdsQuery(userIds));
    }
}
