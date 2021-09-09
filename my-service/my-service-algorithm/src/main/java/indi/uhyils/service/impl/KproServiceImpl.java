package indi.uhyils.service.impl;

import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.DTO.request.DbInformation;
import indi.uhyils.service.KproService;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.kpro.KproUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Service;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月09日 21时23分
 */
@Service
public class KproServiceImpl implements KproService {

    @Override
    public Map<String, String> projectGenerate(List<DbInformation> list) {
        List<HashMap<String, String>> resultList = new ArrayList<>();
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
                    AssertUtil.assertTrue(false, "暂时不支持数据库类型");
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
        return result;
    }
}
