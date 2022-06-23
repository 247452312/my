package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ParamAssembler;
import indi.uhyils.dao.ParamDao;
import indi.uhyils.pojo.DO.ParamDO;
import indi.uhyils.pojo.DTO.ParamDTO;
import indi.uhyils.pojo.entity.Param;
import indi.uhyils.redis.RedisPool;
import indi.uhyils.redis.param.SysParamEnum;
import indi.uhyils.redis.param.SystemParamContext;
import indi.uhyils.repository.ParamRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.Asserts;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 系统参数表(Param)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年06月17日 15时53分
 */
@Repository
public class ParamRepositoryImpl extends AbstractRepository<Param, ParamDO, ParamDao, ParamDTO, ParamAssembler> implements ParamRepository {

    @Autowired
    private RedisPool redisPool;

    protected ParamRepositoryImpl(ParamAssembler convert, ParamDao dao) {
        super(convert, dao);
    }

    @Override
    public List<Param> findAllGlobalParam() {
        final LambdaQueryWrapper<ParamDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ParamDO::getUserId, SystemParamContext.REDIS_PARAM_SYSTEM_USER_ID);
        final List<ParamDO> paramDOs = dao.selectList(queryWrapper);
        return assembler.listToEntity(paramDOs);
    }

    @Override
    public void flushParam(Param param) {
        final ParamDO paramDO = param.toData().orElseThrow(() -> Asserts.makeException("刷新系统参数失败"));
        final String key = paramDO.getKey();
        final Optional<SysParamEnum> sysParamEnumOpt = SysParamEnum.parseEnum(key);
        Asserts.assertTrue(sysParamEnumOpt.isPresent(), "参数键值不存在");

        final SysParamEnum sysParamEnum = sysParamEnumOpt.get();
        sysParamEnum.flush(paramDO.getUserId(), paramDO.getValue(), redisPool);
    }
}
