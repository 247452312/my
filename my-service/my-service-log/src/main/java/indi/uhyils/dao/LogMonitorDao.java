package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.mq.pojo.mqinfo.JvmUniqueMark;
import indi.uhyils.pojo.DO.LogMonitorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 14时55分
 */
@Mapper
public interface LogMonitorDao extends DefaultDao<LogMonitorDO> {

    /**
     * 修改结束时间
     *
     * @param id      id
     * @param endTime 要改成的时间
     *
     * @return
     */
    Boolean changeEndTime(@Param("id") Long id, @Param("endTime") long endTime);

    /**
     * 根据JVM唯一标识 获取主表主键
     *
     * @param jvmUniqueMark
     *
     * @return
     */
    Long getIdByJvmUniqueMark(JvmUniqueMark jvmUniqueMark);


    /**
     * 查询监控主表是否重复
     *
     * @param jvmUniqueMark 唯一标示
     *
     * @return 个数
     */
    Integer checkMonitorRepeat(JvmUniqueMark jvmUniqueMark);

    /**
     * 更新监控信息 -> ip和服务名称都存在并且没有停止 -> 说明已经停止 但是没来得及发现
     *
     * @param serviceName 服务名称
     * @param ip          ip
     * @param now         现在时间
     */
    void updateMonitorThatRepeatByIpAndName(@Param("serviceName") String serviceName, @Param("ip") String ip, @Param("now") long now);
}
