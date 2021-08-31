package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ResponseAssembler;
import indi.uhyils.dao.ResponseDao;
import indi.uhyils.pojo.entity.Response;
import indi.uhyils.pojo.DO.ResponseDO;
import indi.uhyils.pojo.DTO.ResponseDTO;
import indi.uhyils.repository.ResponseRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 设备指令回应表(Response)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分28秒
 */
@Repository
public class ResponseRepositoryImpl extends AbstractRepository<Response, ResponseDO, ResponseDao, ResponseDTO, ResponseAssembler> implements ResponseRepository {

    protected ResponseRepositoryImpl(ResponseAssembler convert, ResponseDao dao) {
        super(convert, dao);
    }


}
