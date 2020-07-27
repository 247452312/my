package indi.uhyils.serviceImpl;

import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.request.DbInformation;
import indi.uhyils.pojo.request.ProjectGenerateRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.KproService;
import indi.uhyils.util.kpro.KproUtil;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 项目生成类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 18时31分
 */
@Service(group = "${spring.profiles.active}")
public class KproServiceImpl implements KproService {


    @Override
    public ServiceResult<HashMap<String, String>> projectGenerate(ProjectGenerateRequest request) {
        List<HashMap<String, String>> resultList = new ArrayList<>();
        List<DbInformation> list = request.getList();
        for (DbInformation dbInformation : list) {
            Integer type = dbInformation.getType();
            switch (Objects.requireNonNull(DbTypeEnum.prase(type))) {
                case MYSQL:
                    resultList.add(KproUtil.getMySqlKpro(dbInformation));
                    break;
                case ORACLE:
                    resultList.add(KproUtil.getOracleKpro(dbInformation));
                    break;
                case SQLITE:
                    resultList.add(KproUtil.getSqliteKpro(dbInformation));
                    break;
                default:
                    return ServiceResult.buildFailedResult("暂时不支持数据库类型", null, request);
            }
        }
        HashMap<String, String> result = new HashMap<>();
        for (HashMap<String, String> stringStringHashMap : resultList) {
            result.putAll(stringStringHashMap);
        }
        result.putAll(KproUtil.getOtherKpro());
        return ServiceResult.buildSuccessResult("生成成功", result, request);
    }
}
