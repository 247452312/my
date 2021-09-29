package indi.uhyils.repository;

import indi.uhyils.pojo.DO.RelegationDO;
import indi.uhyils.pojo.entity.Relegation;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 接口降级策略(Relegation)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时33分23秒
 */
public interface RelegationRepository extends BaseEntityRepository<RelegationDO, Relegation> {


    /**
     * 检查重复
     *
     * @param relegation
     *
     * @return
     */
    boolean checkRepeat(Relegation relegation);

    /**
     * 记录服务降级或者恢复时间到redis
     *
     * @param intervalTime
     */
    void markRelegationOptionTime(Long intervalTime);

    /**
     * 检查是否可以操作
     *
     * @return
     */
    boolean checkRelegationOptionTime();

    /**
     * 获取此次需要降级的服务
     *
     * @param level
     *
     * @return
     */
    List<Relegation> findDegradationLowInterfaceByLevel(Long level);

}
