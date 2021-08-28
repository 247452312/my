package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DictItemRepository;
import indi.uhyils.util.CollectionUtil;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月28日 15时03分
 */
public class DictId extends Identifier {

    private List<DictItem> dictItems;

    public DictId(Long id) {
        super(id);
    }

    public void fillItem(DictItemRepository rep) {
        this.dictItems = rep.findItemByDictId(this);
    }

    public List<DictItem> toItem() {
        return dictItems;
    }

    public void cleanItem(DictItemRepository rep) {
        if (CollectionUtil.isEmpty(dictItems)) {
            return;
        }
        rep.remove(dictItems);
    }
}
