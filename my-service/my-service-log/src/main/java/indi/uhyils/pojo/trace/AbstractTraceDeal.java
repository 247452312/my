package indi.uhyils.pojo.trace;

import com.alibaba.nacos.common.utils.Objects;
import indi.uhyils.pojo.DTO.TraceIdDTO;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时36分
 */
public abstract class AbstractTraceDeal<T extends TraceIdDTO> implements TraceDealInterface<T> {

    @Override
    public T doDeal(String traceMsg) {
        traceMsg = traceMsg.substring(1);
        String[] split = traceMsg.split("\\|");
        T entity = getTargetEntity(split);
        if (Objects.equals(entity.getTraceId(), 1L)) {
            return null;
        }
        return entity;
    }

    /**
     * 解析
     *
     * @param split
     *
     * @return
     */
    protected abstract T getTargetEntity(String[] split);


}
