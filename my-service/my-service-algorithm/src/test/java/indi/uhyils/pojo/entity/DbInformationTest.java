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
        dto.setTables(Arrays.asList("sys_call_node", "sys_company", "sys_company_power", "sys_node", "sys_node_link", "sys_provider_interface", "sys_provider_interface_http", "sys_provider_interface_mysql", "sys_provider_interface_param", "sys_provider_interface_rpc"));
        dto.setDbName("my_gateway");
        dto.setUrl("jdbc:mysql://prod:3306/my_gateway");
        dto.setType(DbTypeEnum.MYSQL.getTypeCode());
        dto.setUserName("root");
        dto.setPassword("123456");
        dto.setProjectName("my-service-gateway");
        dto.setPort(3306);
        dto.setAuthor("uhyils <247452312@qq.com>");

        DbInformation db = new DbInformation(dto);

        db.connect();
        db.fillTableInfos();
        Map<String, String> result = db.result();

        KproUtil.saveToLocal("D:\\my\\生成文件", result);

    }
}
