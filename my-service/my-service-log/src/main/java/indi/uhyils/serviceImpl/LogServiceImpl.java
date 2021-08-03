package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.LogDao;
import indi.uhyils.enum_.CacheTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.model.LogEntity;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.LogService;
import indi.uhyils.util.EnumUtils;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
@ReadWriteMark(tables = {"sys_log"})
public class LogServiceImpl extends BaseDefaultServiceImpl<LogEntity> implements LogService {


    @Resource
    private LogDao dao;


    @Override
    public LogDao getDao() {
        return dao;
    }

    public void setDao(LogDao dao) {
        this.dao = dao;
    }


    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, cacheType = CacheTypeEnum.ALL_TYPE)
    public ServiceResult<ArrayList<Map<String, Object>>> getLogTypes(DefaultRequest request) {
        ArrayList<Map<String, Object>> enumList = EnumUtils.getEnumList(ServiceCode.class);
        return ServiceResult.buildSuccessResult("成功获取所有的日志类型", enumList, request);
    }
}
