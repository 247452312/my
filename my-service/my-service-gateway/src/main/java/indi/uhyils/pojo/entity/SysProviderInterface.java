package indi.uhyils.pojo.entity;

import indi.uhyils.enums.SysTableEnum;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.pojo.entity.sys.SysTable;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.GatewayUtil;
import indi.uhyils.util.StringUtil;
import java.util.Map;
import javafx.util.Pair;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月01日 11时02分
 */
public class SysProviderInterface extends ProviderInterface {

    /**
     * 系统数据库名称
     */
    private String database;

    /**
     * 表
     */
    private String table;


    public SysProviderInterface(String path, Map<String, String> header, Map<String, Object> params) {
        super(true);
        final Pair<String, String> stringStringPair = GatewayUtil.splitDataBaseUrl(path);
        this.database = stringStringPair.getKey();
        this.table = stringStringPair.getValue();
        fillSqlInfo(null, header, params);
    }

    @Override
    public NodeInvokeResult getResult() {
        Asserts.assertTrue(StringUtil.isNotEmpty(database) && StringUtil.isNotEmpty(table), "数据库不存在或表不存在");
        final SysTableEnum parse = SysTableEnum.parse(database, table);
        final SysTable sysTable = parse.getSysTable(this.params);
        return sysTable.getResult();
    }
}
