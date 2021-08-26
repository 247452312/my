package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.DeviceCallbackConvert;
import indi.uhyils.dao.DeviceCallbackDao;
import indi.uhyils.pojo.entity.DeviceCallback;
import indi.uhyils.pojo.DO.DeviceCallbackDO;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.repository.DeviceCallbackRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 设备回调表(DeviceCallback)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分36秒
 */
@Repository
public class DeviceCallbackRepositoryImpl extends AbstractRepository<DeviceCallback, DeviceCallbackDO, DeviceCallbackDao> implements DeviceCallbackRepository {

    protected DeviceCallbackRepositoryImpl(DeviceCallbackConvert convert, DeviceCallbackDao dao) {
        super(convert, dao);
    }


}
