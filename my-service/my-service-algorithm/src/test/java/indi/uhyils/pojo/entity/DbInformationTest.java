package indi.uhyils.pojo.entity;

import indi.uhyils.enum_.DbTypeEnum;
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
        dto.setTables(Arrays.asList("sys_platform_publish_node"));
        dto.setDbName("my_platform");
        dto.setUrl("jdbc:mysql://prod:3306/my_platform");
        dto.setType(DbTypeEnum.MYSQL.getTypeCode());
        dto.setUserName("root");
        dto.setPassword("123456");
        dto.setProjectName("my-service-platform");
        dto.setPort(3306);
        dto.setAuthor("uhyils <247452312@qq.com>");

        DbInformation db = new DbInformation(dto);

        db.connect();
        db.fillTableInfos();
        Map<String, String> result = db.result();

        KproUtil.saveToLocal("D:\\my\\生成文件", result);

    }
}
