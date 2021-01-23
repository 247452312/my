package indi.uhyils.serviceImpl;

import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.request.DbInformation;
import indi.uhyils.pojo.request.ProjectGenerateRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.KproService;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.kpro.KproUtil;
import indi.uhyils.rpc.annotation.RpcService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 项目生成类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 18时31分
 */
@RpcService
public class KproServiceImpl implements KproService {


    @Override
    public ServiceResult<HashMap<String, String>> projectGenerate(ProjectGenerateRequest request) {
        List<HashMap<String, String>> resultList = new ArrayList<>();
        List<DbInformation> list = request.getList();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分");
        String dateFormat = simpleDateFormat.format(date);
        for (DbInformation dbInformation : list) {
            Integer type = dbInformation.getType();
            switch (Objects.requireNonNull(DbTypeEnum.prase(type))) {
                case MYSQL:
                    resultList.add(KproUtil.getMySqlKpro(dbInformation, dateFormat));
                    break;
                case ORACLE:
                    resultList.add(KproUtil.getOracleKpro(dbInformation, dateFormat));
                    break;
                case SQLITE:
                    resultList.add(KproUtil.getSqliteKpro(dbInformation, dateFormat));
                    break;
                default:
                    return ServiceResult.buildFailedResult("暂时不支持数据库类型", null, request);
            }
        }
        HashMap<String, String> result = new HashMap<>(16);
        for (HashMap<String, String> stringStringHashMap : resultList) {
            result.putAll(stringStringHashMap);
        }
        for (Map.Entry<String, String> entry : result.entrySet()) {
            String filePath = entry.getKey();
            String fileContent = entry.getValue();
            try {
                filePath = "d:/temp/" + filePath;
                File file = new File(filePath);
                String dir = filePath.substring(0, filePath.lastIndexOf("/"));
                File dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(filePath);
                fw.write(fileContent);
                fw.close();
            } catch (IOException e) {
                LogUtil.error(this, e);
            }
        }
        return ServiceResult.buildSuccessResult("生成成功", result, request);
    }
}
