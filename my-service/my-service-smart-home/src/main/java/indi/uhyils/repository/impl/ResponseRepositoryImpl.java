package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.ResponseConvert;
import indi.uhyils.dao.ResponseDao;
import indi.uhyils.pojo.entity.Response;
import indi.uhyils.pojo.DO.ResponseDO;
import indi.uhyils.repository.ResponseRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 设备指令回应表(Response)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分25秒
 */
@Repository
public class ResponseRepositoryImpl extends AbstractRepository<Response, ResponseDO, ResponseDao> implements ResponseRepository {

    protected ResponseRepositoryImpl(ResponseAssembler convert, ResponseDao dao) {
        super(convert, dao);
    }


}
