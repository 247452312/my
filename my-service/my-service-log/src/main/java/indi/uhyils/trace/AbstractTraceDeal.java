package indi.uhyils.trace;

import com.alibaba.nacos.common.utils.Objects;
import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.exception.IdGenerationException;
import indi.uhyils.pojo.model.TraceIdDoEntity;
import indi.uhyils.util.DefaultRequestBuildUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时36分
 */
public abstract class AbstractTraceDeal<T extends TraceIdDoEntity> implements TraceDealInterface<T> {

    private DefaultDao<T> dao;

    @Override
    public void doDeal(String traceMsg) throws IdGenerationException, InterruptedException {
        traceMsg = traceMsg.substring(1);
        String[] split = traceMsg.split("\\|");
        T entity = getTargetEntity(split);
        if (Objects.equals(entity.getTraceId(), 1L)) {
            return;
        }
        entity.preInsert(DefaultRequestBuildUtil.getAdminDefaultRequest());
        dao.insert(entity);
    }

    /**
     * 解析
     *
     * @param split
     *
     * @return
     */
    protected abstract T getTargetEntity(String[] split);

    @Override
    public void init(DefaultDao<T> dao) {
        this.dao = dao;
    }


}
