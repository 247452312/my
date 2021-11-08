package indi.uhyils.repository;

import com.alibaba.fastjson.JSON;
import indi.uhyils.pojo.entity.DbInfo;
import indi.uhyils.pojo.entity.interfaces.InterfaceInfo;
import indi.uhyils.repository.base.BaseRepository;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月28日 09时08分
 */
public interface DbManualRepository extends BaseRepository {

    /**
     * 获取连接
     *
     * @param interfaceInfo
     *
     * @return
     */
    Connection getConn(InterfaceInfo interfaceInfo) throws SQLException;
    /**
     * 获取连接
     *
     * @param dbInfo
     *
     * @return
     */
    Connection getConn(DbInfo dbInfo) throws SQLException;
}
