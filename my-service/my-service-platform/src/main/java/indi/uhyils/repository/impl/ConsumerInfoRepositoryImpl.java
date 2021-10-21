package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ConsumerInfoAssembler;
import indi.uhyils.dao.ConsumerInfoDao;
import indi.uhyils.enum_.ConsumerStatusEnum;
import indi.uhyils.pojo.DO.ConsumerInfoDO;
import indi.uhyils.pojo.DTO.ConsumerInfoDTO;
import indi.uhyils.pojo.entity.ConsumerInfo;
import indi.uhyils.repository.ConsumerInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 服务消费方信息表(ConsumerInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@Repository
public class ConsumerInfoRepositoryImpl extends AbstractRepository<ConsumerInfo, ConsumerInfoDO, ConsumerInfoDao, ConsumerInfoDTO, ConsumerInfoAssembler> implements ConsumerInfoRepository {

    protected ConsumerInfoRepositoryImpl(ConsumerInfoAssembler convert, ConsumerInfoDao dao) {
        super(convert, dao);
    }


    @Override
    public boolean checkNameRepeat(String name) {
        LambdaQueryWrapper<ConsumerInfoDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ConsumerInfoDO::getName, name);
        queryWrapper.eq(ConsumerInfoDO::getStatus, ConsumerStatusEnum.USING.getCode());
        Long count = dao.selectCount(queryWrapper);
        // 数量不为零,就代表数据库中存在
        return count != 0;
    }
}
