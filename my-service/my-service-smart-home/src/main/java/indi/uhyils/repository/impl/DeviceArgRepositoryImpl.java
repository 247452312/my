package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.DeviceArgConvert;
import indi.uhyils.dao.DeviceArgDao;
import indi.uhyils.pojo.entity.DeviceArg;
import indi.uhyils.pojo.DO.DeviceArgDO;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.repository.DeviceArgRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 设备参数表(DeviceArg)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分30秒
 */
@Repository
public class DeviceArgRepositoryImpl extends AbstractRepository<DeviceArg, DeviceArgDO, DeviceArgDao> implements DeviceArgRepository {

    protected DeviceArgRepositoryImpl(DeviceArgConvert convert, DeviceArgDao dao) {
        super(convert, dao);
    }


}
