package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.TraceDetailEntity;
import indi.uhyils.pojo.request.GetTraceDetailByHashCodeRequest;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
@Mapper
public interface TraceDetailDao extends DefaultDao<TraceDetailEntity> {


    /**
     * 获取链路详情
     *
     * @param request
     *
     * @return
     */
    TraceDetailEntity getTraceDetailByHashCode(GetTraceDetailByHashCodeRequest request);
}
