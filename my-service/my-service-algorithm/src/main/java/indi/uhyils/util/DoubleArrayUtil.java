package indi.uhyils.util;

/**
 * double 修改方法
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 15时25分
 */
public class DoubleArrayUtil {

    public static Double[][] cenvert(double[][] doubles) {
        if (doubles == null || doubles.length == 0) {
            return null;
        }
        Double[][] result = new Double[doubles.length][doubles[0].length];
        for (int i = 0; i < doubles.length; i++) {
            for (int j = 0; j < doubles[0].length; j++) {
                result[i][j] = doubles[i][j];
            }
        }
        return result;
    }
}
