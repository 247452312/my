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
        dto.setTables(Arrays.asList("hcos_operate_record"));
        dto.setDbName("test");
        dto.setUrl("jdbc:mysql://my:3306/hcos_coordination?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true&autoconnect=true&failOverReadOnly=false&useSSL=false");
        dto.setType(DbTypeEnum.MYSQL.getTypeCode());
        dto.setUserName("test");
        dto.setPassword("test");
        dto.setProjectName("test");
        dto.setPort(3306);
        dto.setAuthor("uhyils <247452312@qq.com>");

        DbInformation db = new DbInformation(dto);

        db.connect();
        db.fillTableInfos();
        Map<String, String> result = db.result();

        KproUtil.saveToLocal("D:\\my\\生成文件\\hcos", result);

    }
}
