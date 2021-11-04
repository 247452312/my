package indi.uhyils.protocol.mysql.pojo.response;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 08时20分
 */
public abstract class AbstractMysqlResponse implements MysqlResponse {

    @Override
    public byte[] toByte() {
        byte[] bytes = toByteNoMarkIndex();
        byte[] result = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0, result, 1, bytes.length);
        result[0] = getFirstByte();
        return result;
    }
}
