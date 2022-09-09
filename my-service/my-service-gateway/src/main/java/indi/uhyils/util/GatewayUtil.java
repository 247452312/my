package indi.uhyils.util;

import indi.uhyils.mysql.content.MysqlContent;
import javafx.util.Pair;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月01日 09时40分
 */
public final class GatewayUtil {

    protected GatewayUtil() {
        throw new RuntimeException("不能初始化");
    }

    /**
     * 切分数据库url
     *
     * @return
     */
    public static Pair<String, String> splitDataBaseUrl(String url) {
        final int separatorIndex = url.indexOf(MysqlContent.PATH_SEPARATOR);
        if (separatorIndex == -1) {
            return new Pair<>(null, url);
        }
        final String database = url.substring(0, separatorIndex);
        final String table = url.substring(separatorIndex + 1);
        return new Pair<>(database, table);
    }


}
