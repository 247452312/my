package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.InterfaceInfoAssembler;
import indi.uhyils.dao.InterfaceInfoDao;
import indi.uhyils.pojo.entity.InterfaceInfo;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.DTO.InterfaceInfoDTO;
import indi.uhyils.repository.InterfaceInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 接口信息表(InterfaceInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Repository
public class InterfaceInfoRepositoryImpl extends AbstractRepository<InterfaceInfo, InterfaceInfoDO, InterfaceInfoDao, InterfaceInfoDTO, InterfaceInfoAssembler> implements InterfaceInfoRepository {

    protected InterfaceInfoRepositoryImpl(InterfaceInfoAssembler convert, InterfaceInfoDao dao) {
        super(convert, dao);
    }


}
