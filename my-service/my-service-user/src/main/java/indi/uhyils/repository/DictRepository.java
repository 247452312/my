package indi.uhyils.repository;

import indi.uhyils.pojo.DO.DictDO;
import indi.uhyils.pojo.entity.Dict;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 数据字典(Dict)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分37秒
 */
public interface DictRepository extends BaseEntityRepository<DictDO, Dict> {

}
