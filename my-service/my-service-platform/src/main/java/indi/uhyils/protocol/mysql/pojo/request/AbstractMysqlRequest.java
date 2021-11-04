package indi.uhyils.protocol.mysql.pojo.request;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 15时03分
 */
public abstract class AbstractMysqlRequest implements MysqlRequest {

    /**
     * 要处理的字节数组
     */
    protected byte[] mysqlBytes;

    @Override
    public void load(byte[] mysqlBytes) {
        this.mysqlBytes = mysqlBytes;
        load();
    }

    /**
     * 加载
     */
    protected abstract void load();
}
