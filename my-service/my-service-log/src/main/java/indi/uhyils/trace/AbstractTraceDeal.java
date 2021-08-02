package indi.uhyils.trace;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.exception.IdGenerationException;
import indi.uhyils.pojo.model.base.BaseDoEntity;
import indi.uhyils.util.DefaultRequestBuildUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时36分
 */
public abstract class AbstractTraceDeal implements TraceDealInterface {

    private DefaultDao dao;

    @Override
    public void doDeal(String traceMsg) throws IdGenerationException, InterruptedException {
        traceMsg = traceMsg.substring(1);
        String[] split = traceMsg.split("\\|");
        BaseDoEntity entity = getTargetEntity(split);
        entity.preInsert(DefaultRequestBuildUtil.getAdminDefaultRequest());
        dao.insert(entity);
    }

    /**
     * 解析
     *
     * @param split
     */
    protected abstract BaseDoEntity getTargetEntity(String[] split);

    @Override
    public void init(DefaultDao dao) {
        this.dao = dao;
    }


}
