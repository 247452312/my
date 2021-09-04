package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderNodeFieldAssembler;
import indi.uhyils.dao.OrderNodeFieldDao;
import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import indi.uhyils.pojo.DTO.OrderNodeFieldDTO;
import indi.uhyils.pojo.entity.OrderNodeField;
import indi.uhyils.repository.OrderNodeFieldRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.List;


/**
 * 工单节点属性样例表(OrderNodeField)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分24秒
 */
@Repository
public class OrderNodeFieldRepositoryImpl extends AbstractRepository<OrderNodeField, OrderNodeFieldDO, OrderNodeFieldDao, OrderNodeFieldDTO, OrderNodeFieldAssembler> implements OrderNodeFieldRepository {

    protected OrderNodeFieldRepositoryImpl(OrderNodeFieldAssembler convert, OrderNodeFieldDao dao) {
        super(convert, dao);
    }


    @Override
    public List<OrderNodeField> findByNodeId(Long nodeId) {
        List<OrderNodeFieldDO> byOrderNodeId = dao.getByOrderNodeId(nodeId);
        return assembler.listToEntity(byOrderNodeId);
    }

    @Override
    public List<OrderNodeField> findByNodeIds(List<Long> nodeIds) {
        List<OrderNodeFieldDO> byOrderNodeIds = dao.getByOrderNodeIds(nodeIds);
        return assembler.listToEntity(byOrderNodeIds);
    }

}
