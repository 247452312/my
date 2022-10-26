package indi.uhyils.pojo.entity;

import indi.uhyils.enums.DbTypeEnum;
import indi.uhyils.pojo.DTO.request.DbInformationDTO;
import indi.uhyils.util.KproUtil;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月08日 16时16分
 */
class DbInformationTest {

    @Test
    void fillTableInfos() {
        DbInformationDTO dto = new DbInformationDTO();
        dto.setTables(Arrays.asList("i_blood_sugar_result"));
        dto.setDbName("lis");
        dto.setUrl("jdbc:mysql://rm-bp1wxt5s354kmj3i7.mysql.rds.aliyuncs.com:3306/lis?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false");
        dto.setType(DbTypeEnum.MYSQL.getTypeCode());
        dto.setUserName("xnyl");
        dto.setPassword("XUqk5RQ3d#jN");
        dto.setProjectName("lis-service");
        dto.setPort(3306);
        dto.setAuthor("uhyils <247452312@qq.com>");

        DbInformation db = new DbInformation(dto);

        db.connect();
        db.fillTableInfos();
        Map<String, String> result = db.result();

        KproUtil.saveToLocal("D:\\my\\生成文件\\lis", result);

    }
}
