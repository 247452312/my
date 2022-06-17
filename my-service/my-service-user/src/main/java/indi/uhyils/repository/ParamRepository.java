package indi.uhyils.repository;

import indi.uhyils.pojo.DO.ParamDO;
import indi.uhyils.pojo.entity.Param;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 系统参数表(Param)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年06月17日 15时53分
 */
public interface ParamRepository extends BaseEntityRepository<ParamDO, Param> {

    /**
     * 获取所有全局参数
     *
     * @return
     */
    List<Param> findAllGlobalParam();

    /**
     * 刷新指定的参数到redis
     *
     * @param userId
     * @param key
     */
    void flushParam(Long userId, String key);
}
