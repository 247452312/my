package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DictItemAssembler;
import indi.uhyils.dao.DictItemDao;
import indi.uhyils.pojo.DO.DictItemDO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.request.GetByItemArgsQuery;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.entity.Dict;
import indi.uhyils.pojo.entity.DictItem;
import indi.uhyils.repository.DictItemRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 数据字典子项(DictItem)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分42秒
 */
@Repository
public class DictItemRepositoryImpl extends AbstractRepository<DictItem, DictItemDO, DictItemDao, DictItemDTO, DictItemAssembler> implements DictItemRepository {

    protected DictItemRepositoryImpl(DictItemAssembler convert, DictItemDao dao) {
        super(convert, dao);
    }


    @Override
    public List<DictItem> findItemByDictId(Dict dictId) {
        ArrayList<DictItemDO> byDictId = dao.getByDictId(dictId.getId().getId());
        return byDictId.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public Page<DictItem> find(GetByItemArgsQuery query) {
        List<Arg> args = query.getArgs();
        Arg arg = new Arg();
        arg.setName("dict_id");
        arg.setSymbol("=");
        arg.setData(query.getDictId());
        args.add(arg);
        return find(query);
    }

    @Override
    public List<DictItem> findItemByDictCode(String itemCode) {
        ArrayList<DictItemDO> byCode = dao.getByCode(itemCode);
        return byCode.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

}
