package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.TraceDetailStatisticsView;
import indi.uhyils.pojo.DO.TraceInfoDO;
import indi.uhyils.pojo.DTO.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.cqe.query.BlackQuery;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年07月31日 06时43分
 */
@Mapper
public interface TraceInfoDao extends DefaultDao<TraceInfoDO> {

    /**
     * 获取从开始时间到现在的前台请求次数
     *
     * @param code
     * @param time 开始时间
     *
     * @return 从开始时间到现在的前台请求次数
     */
    Long getCountByTypeAndStartTime(@Param("code") Integer code, @Param("time") Long time);

    /**
     * 获取traceInfo
     *
     * @param request
     *
     * @return
     */
    List<TraceInfoDO> getTraceInfoByArgAndPage(GetTraceInfoByArgAndPageRequest request);

    /**
     * 获取traceInfo
     *
     * @param request
     *
     * @return
     */
    Integer getTraceInfoByArgAndPageCount(GetTraceInfoByArgAndPageRequest request);

    /**
     * 获取统计信息
     *
     * @return
     */
    List<TraceDetailStatisticsView> getTraceStatistics(BlackQuery request);

    /**
     * 获取统计信息的数量
     *
     * @param request
     *
     * @return
     */
    Integer getTraceStatisticsCount(BlackQuery request);

    /**
     * 根据traceId 和 rpcId 获取详情
     *
     * @param traceId
     * @param rpcId
     *
     * @return
     */
    List<TraceInfoDO> getLinkByTraceIdAndRpcIdPrefix(@Param("traceId") Long traceId, @Param("rpcId") String rpcId);

    /**
     * 根据traceId 获取整个链路
     *
     * @param traceId
     *
     * @return
     */
    ArrayList<TraceInfoDO> getTraceInfoByTraceId(Long traceId);
}
