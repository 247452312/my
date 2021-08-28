package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DictItemRepository;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月28日 15时13分
 */
public class DictItemId extends Identifier {

    public DictItemId(Long id) {
        super(id);
    }

    public DictItem completion(DictItemRepository dictItemRepository) {
        return dictItemRepository.find(this);
    }
}
