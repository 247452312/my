package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.TraceInfoEntity;
import indi.uhyils.pojo.request.GetLinkByTraceIdRequest;
import indi.uhyils.pojo.request.base.DefaultPageRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年07月31日 06时43分
 */
@Mapper
public interface TraceInfoDao extends DefaultDao<TraceInfoEntity> {


    /**
     * 根据traceId获取链路
     *
     * @param value
     *
     * @return
     */
    List<TraceInfoEntity> getByTraceId(Long value);

    /**
     * 分页获取traceId
     *
     * @param request 请求
     *
     * @return
     */
    List<Long> getTraceIdByPage(DefaultPageRequest request);

    /**
     * 获取traceId分页数据
     *
     * @param request
     *
     * @return
     */
    Integer getTraceIdByPageCount(DefaultPageRequest request);

    /**
     * 通过traceId 获取link
     *
     * @param traceId
     *
     * @return
     */
    List<TraceInfoEntity> getLinkByTraceId(GetLinkByTraceIdRequest traceId);
}