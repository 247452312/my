package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.ParamDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.repository.ParamRepository;
import java.util.List;

/**
 * 系统参数表(Param)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月17日 15时53分
 */
public class Param extends AbstractDoEntity<ParamDO> {

    @Default
    public Param(ParamDO data) {
        super(data);
    }

    public Param(Long id) {
        super(id, new ParamDO());
    }

    public Param() {
    }

    /**
     * 刷新所有的全局参数
     *
     * @param repository
     *
     * @return
     */
    public boolean flushAllGlobal(ParamRepository repository) {
        List<Param> result = repository.findAllGlobalParam();
        for (Param param : result) {
            param.flushToRedis(repository);
        }
        return true;
    }

    /**
     * 刷新当前参数到redis
     */
    private void flushToRedis(ParamRepository repository) {
        repository.flushParam(this);
    }
}
