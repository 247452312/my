package indi.uhyils.util.kpro;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月27日 08时03分
 */
public class KproStringUtil {
    /**
     * 数据库名称转java文件名称
     *
     * @param dbName 数据库名称
     * @return java model名称
     */
    public static String dealDbNameToJavaFileName(String dbName) {
        String[] tableNameSplits = dbName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String tableNameSplit : tableNameSplits) {
            sb.append(tableNameSplit.substring(0, 1).toUpperCase());
            sb.append(tableNameSplit.substring(1));
        }
        return sb.toString();
    }
}
