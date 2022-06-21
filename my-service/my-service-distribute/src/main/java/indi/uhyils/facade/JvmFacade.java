package indi.uhyils.facade;

import indi.uhyils.pojo.DTO.response.JvmDataStatisticsDTO;
import indi.uhyils.pojo.DTO.response.JvmInfoLogDTO;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月10日 08时29分
 */
public interface JvmFacade extends BaseFacade {

    /**
     * jvm统计信息
     *
     * @return
     */
    JvmDataStatisticsDTO jvmStatisticDate();

    /**
     * jvm日志信息
     *
     * @return
     */
    JvmInfoLogDTO jvmInfoLog();
}
