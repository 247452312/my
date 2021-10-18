package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.DO.DbInfoDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DbInfoRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接表(DbInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分07秒
 */
public class DbInfo extends SourceInfo<DbInfoDO> {

    private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    @Default
    public DbInfo(DbInfoDO data) {
        super(data);
    }

    public DbInfo(Long id) {
        super(id, new DbInfoDO());
    }

    public DbInfo(Long id, DbInfoRepository rep) {
        super(id, new DbInfoDO());
        completion(rep);
    }

    public DbInfo(Identifier id) {
        super(id, new DbInfoDO());
    }

    @Override
    public Boolean testConnect() throws Exception {
        DbInfoDO dbInfoDO = toData();
        Asserts.assertTrue(dbInfoDO != null);
        DbTypeEnum paras = DbTypeEnum.parse(dbInfoDO.getType());
        switch (paras) {
            case ORACLE:
                return testConnect(dbInfoDO, ORACLE_DRIVER);
            case MYSQL:
                return testConnect(dbInfoDO, MYSQL_DRIVER);
            default:
                Asserts.assertTrue(false, "暂不支持数据库类型");
        }
        return false;
    }

    /**
     * 尝试连接数据库
     *
     * @param dbInfoDO
     *
     * @return
     *
     * @throws ClassNotFoundException
     */
    private Boolean testConnect(DbInfoDO dbInfoDO, String driver) throws ClassNotFoundException {
        Class.forName(driver);
        try {
            Connection con = DriverManager.getConnection(dbInfoDO.getUrl(), dbInfoDO.getUsername(), dbInfoDO.getPassword());
            con.close();
        } catch (SQLException e) {
            LogUtil.error(e, "数据库连接失败,url:{}", dbInfoDO.getUrl());
            return false;
        }
        return true;
    }

}
