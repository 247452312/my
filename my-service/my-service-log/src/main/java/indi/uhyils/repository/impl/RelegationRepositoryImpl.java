package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.RelegationAssembler;
import indi.uhyils.dao.RelegationDao;
import indi.uhyils.pojo.DO.RelegationDO;
import indi.uhyils.pojo.DTO.RelegationDTO;
import indi.uhyils.pojo.entity.Relegation;
import indi.uhyils.repository.RelegationRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 接口降级策略(Relegation)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时33分23秒
 */
@Repository
public class RelegationRepositoryImpl extends AbstractRepository<Relegation, RelegationDO, RelegationDao, RelegationDTO, RelegationAssembler> implements RelegationRepository {

    protected RelegationRepositoryImpl(RelegationAssembler convert, RelegationDao dao) {
        super(convert, dao);
    }


    @Override
    public boolean checkRepeat(Relegation relegation) {
        RelegationDO relegationDO = relegation.toDo();
        LambdaQueryWrapper<RelegationDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.le(RelegationDO::getServiceName, relegationDO.getServiceName());
        queryWrapper.le(RelegationDO::getMethodName, relegationDO.getMethodName());
        queryWrapper.le(RelegationDO::getParamLength, relegationDO.getParamLength() != null ? relegationDO.getParamLength() : 1);
        Long count = dao.selectCount(queryWrapper);
        return count != 0;
    }
}
