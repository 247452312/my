package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.BaseType;
import indi.uhyils.repository.DictItemRepository;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月28日 15时37分
 */
public class DictItemCode implements BaseType {

    private String code;

    public DictItemCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public List<DictItem> findItem(DictItemRepository dictItemRepository) {
        return dictItemRepository.findItemByDictCode(this);
    }

}
