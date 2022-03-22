package indi.uhyils.util.mysql.pojo;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月06日 11时07分
 */
public interface MysqlServerObserver {

    /**
     * 获取mysql系统信息
     *
     * @return
     */
    MysqlServerInfo getMysqlServerInfo();

}
