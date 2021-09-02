package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderBaseNodeFieldAssembler;
import indi.uhyils.dao.OrderBaseNodeFieldDao;
import indi.uhyils.pojo.DO.OrderBaseNodeFieldDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeFieldDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeField;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.OrderBaseNodeFieldRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 工单节点属性样例表(OrderBaseNodeField)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分01秒
 */
@Repository
public class OrderBaseNodeFieldRepositoryImpl extends AbstractRepository<OrderBaseNodeField, OrderBaseNodeFieldDO, OrderBaseNodeFieldDao, OrderBaseNodeFieldDTO, OrderBaseNodeFieldAssembler> implements OrderBaseNodeFieldRepository {

    protected OrderBaseNodeFieldRepositoryImpl(OrderBaseNodeFieldAssembler convert, OrderBaseNodeFieldDao dao) {
        super(convert, dao);
    }


    @Override
    public List<OrderBaseNodeField> findNodeFieldByNodes(List<Identifier> nodeIds) {
        List<OrderBaseNodeFieldDO> byOrderNodeIds = dao.getByOrderNodeIds(nodeIds.stream().map(Identifier::getId).collect(Collectors.toList()));
        return assembler.listToEntity(byOrderNodeIds);
    }
}
