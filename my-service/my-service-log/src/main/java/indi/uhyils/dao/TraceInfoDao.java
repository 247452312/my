package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.TraceInfoEntity;
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
    List<TraceInfoEntity> getByTraceId(String value);
}