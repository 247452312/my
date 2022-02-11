package indi.uhyils.repository;

import indi.uhyils.pojo.DO.DynamicCodeHistoryDO;
import indi.uhyils.pojo.entity.DynamicCodeHistory;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
* 动态代码历史记录表(DynamicCodeHistory)表 数据仓库层
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年02月11日 18时53分
*/
public interface DynamicCodeHistoryRepository extends BaseEntityRepository<DynamicCodeHistoryDO, DynamicCodeHistory> {
}
